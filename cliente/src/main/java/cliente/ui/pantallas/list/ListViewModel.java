package cliente.ui.pantallas.list;

import cliente.domain.usecases.JuegoUseCase;
import common.Constant;
import jakarta.inject.Inject;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


import java.util.List;

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
                        state.set(new ListState(result.get(), state.get().getJuegosBorrarFiltro(), null));
                    } else {
                        state.set(new ListState(null, state.get().getJuegosBorrarFiltro(), Constant.NO_SE_HA_PODIDO_CARGAR));
                    }


                });
    }

    public void cargarListPorFilter(String filter){
        juegoUseCase.getJuegosDeCompany(filter)
                .subscribe(result->{
                    if (result.isRight()){
                        state.set(new ListState(state.get().getJuegosBorrarMultiple(), result.get(),null));
                    }else {
                        state.set(new ListState(state.get().getJuegosBorrarMultiple(),null,Constant.NO_SE_HA_PODIDO_CARGAR));
                    }

                });

    }
    public void deleteMultiple(List<String>juegos){
        juegoUseCase.deleteJuegoMultiple(juegos)
                .subscribe(result->{
                    if (result.isRight()){
                       loadList();
                    }else {
                        state.set(new ListState(state.get().getJuegosBorrarMultiple(),state.get().getJuegosBorrarFiltro(), null));
                    }



                });

    }
    public void deleteJuegosDeCompany(String company){
        juegoUseCase.deleteJuegosDeCompany(company).subscribe(response->{
            if (response.isRight()){
                loadList();
            }else {
                state.set(new ListState(state.get().getJuegosBorrarMultiple(),state.get().getJuegosBorrarFiltro(), null));
            }

        });

    }
}

