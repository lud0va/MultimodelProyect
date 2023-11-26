package cliente.ui.pantallas.login;

import cliente.data.CuentaDao;
import cliente.useCases.CuentaUseCase;

import errores.ApiError;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.time.LocalDateTime;

public class LoginViewModel {


    private final CuentaUseCase cuentaUseCase;

    @Inject
    public LoginViewModel(CuentaUseCase cuentaUseCase) {

        this.cuentaUseCase = cuentaUseCase;

        this.state = new SimpleObjectProperty<>(new LoginState(false,null));
    }

    private final ObjectProperty<LoginState> state;
    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }

    public void doLogin(String user,String name){

        cuentaUseCase.doLogin(user, name)
                .subscribe(result -> {
                    if (result.isRight()) {
                        // El login fue exitoso, actualiza el estado a true
                        state.set(new LoginState(true,null));
                    } else {
                        // El login falló, puedes manejar el error si es necesario
                        state.set(new LoginState(false,new ApiError("Error: nombre o contraseña invalidos", LocalDateTime.now()).getMessage()));
                    }
                });

    }










}
