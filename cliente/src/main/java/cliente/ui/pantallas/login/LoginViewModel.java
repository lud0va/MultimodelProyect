package cliente.ui.pantallas.login;

import cliente.domain.usecases.CuentaUseCase;

import cliente.domain.usecases.JugadorUseCase;
import common.Constant;
import errores.ApiError;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Cuenta;
import model.Jugador;

import java.time.LocalDateTime;

public class LoginViewModel {


    private final CuentaUseCase cuentaUseCase;
    private final JugadorUseCase jugadorUseCase;

    @Inject
    public LoginViewModel(CuentaUseCase cuentaUseCase, JugadorUseCase jugadorUseCase) {

        this.cuentaUseCase = cuentaUseCase;
        this.jugadorUseCase = jugadorUseCase;

        this.state = new SimpleObjectProperty<>(new LoginState(null, false, null));
    }

    private final ObjectProperty<LoginState> state;

    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void doLogin(String user, String name) {

        cuentaUseCase.doLogin(user, name)
                .subscribe(result -> {
                    if (result.isRight()) {

                        state.set(new LoginState(null, true, null));
                    } else {

                        state.set(new LoginState(null, false, new ApiError(Constant.ERROR_NOMBRE_O_CONTRASEÑA_INVALIDOS, LocalDateTime.now()).getMessage()));
                    }
                });

    }

    public void doSign(String nombre, String apellido, String user, String passw, String edad, String mail) {
        if (state.get().getCuenta() == null) {
            Cuenta cuenta = new Cuenta(user, passw, mail);

            cuentaUseCase.addCuenta(cuenta).subscribe(result -> {
                if (result.isRight()) {
                    state.set(new LoginState(cuenta, true, null));


                }


            });

        }
        if (state.get().getCuenta() != null && !state.get().getLogged()) {
            jugadorUseCase.addJugador(new Jugador(nombre, apellido, Integer.parseInt(edad))).subscribe(res -> {
                if (res.isRight()) {
                    state.set(new LoginState(null, true, null));
                } else {
                       state.set(new LoginState(null, false, new ApiError(Constant.ERROR_AL_ADD_AL_JUGADOR, LocalDateTime.now()).getMessage()));
                }


            });
        }
    }

}

