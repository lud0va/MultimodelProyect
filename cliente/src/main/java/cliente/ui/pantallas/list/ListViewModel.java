package cliente.ui.pantallas.list;

import cliente.domain.useCases.useCases.JuegoUseCase;
import common.Constant;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class ListViewModel {
    private final JuegoUseCase juegoUseCase;

    @Inject
    public ListViewModel(JuegoUseCase juegoUseCase) {
        this.juegoUseCase = juegoUseCase;
        this.state = new SimpleObjectProperty<>(new ListState(null, null, null));
    }

    private final ObjectProperty<ListState> state;

    public ReadOnlyObjectProperty<ListState> getState() {
        return state;
    }

    public void loadList() {
        juegoUseCase.getJuegos()
                .subscribe(result -> {
                    if (result.isRight()) {
                        state.set(new ListState(result.get(), null, null));
                    } else {
                        state.set(new ListState(null, null, Constant.NO_SE_HA_PODIDO_CARGAR));
                    }


                });
    }
}

