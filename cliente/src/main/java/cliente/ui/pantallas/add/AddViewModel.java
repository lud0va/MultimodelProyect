package cliente.ui.pantallas.add;

import cliente.domain.usecases.JuegoUseCase;

import common.Constant;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import model.Juego;

public class AddViewModel {
    private final JuegoUseCase juegoUseCase;

    public AddViewModel(JuegoUseCase juegoUseCase) {
        this.juegoUseCase = juegoUseCase;
        this.state = new SimpleObjectProperty<>(new AddState(null, null));

    }

    private final ObjectProperty<AddState> state;

    public ReadOnlyObjectProperty<AddState> getState() {
        return state;
    }

    public void loadList() {
        juegoUseCase.getJuegos()
                .subscribe(result -> {
                    if (result.isRight()) {
                        state.set(new AddState(result.get(), null));
                    } else {
                        state.set(new AddState(state.get().getJuegos(), Constant.NO_SE_HA_PODIDO_CARGAR));
                    }


                });
    }

    public void updateJuego(Juego juego) {
        juegoUseCase.updateJuego(juego)
                .subscribe(result -> {
                    if (result.isRight()) {
                        loadList();
                    } else {
                        state.set(new AddState(state.get().getJuegos(), Constant.NO_SE_HA_PODIDO_CARGAR));

                    }


                });


    }

    public void addJuego(Juego juego) {

        juegoUseCase.addJuego(juego)
                .subscribe(result -> {
                    if (result.isRight()) {
                        loadList();
                    } else {
                        state.set(new AddState(state.get().getJuegos(), Constant.NO_SE_HA_PODIDO_ADD));
                    }

                });


    }


}
