package cliente.ui.pantallas.list;

import cliente.ui.common.BaseScreenController;
import common.Constant;
import jakarta.inject.Inject;
import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import model.Juego;

import java.util.ArrayList;
import java.util.List;

public class ListController extends BaseScreenController {
    @FXML
    private Label mensajeLabel;
    @FXML
    private TableColumn<String,Juego> idBorrar;
    @FXML
    private TableColumn<String,Juego> tituloBorrar;
    @FXML
    private TableView<Juego> tableToDelete;
    @FXML
    private TableView<Juego> tableDeleteMultiple;
    @FXML
    private TableColumn<String,Juego> tituloDelete;
    @FXML
    private TableColumn<String,Juego> pegiDelete;
    @FXML
    private TableColumn<String,Juego> desarolladoraDelete;
    @FXML
    private SplitMenuButton filterCompany;
    @FXML
    private Button buttonDelete;
    @FXML
    private TableView<Juego> tableDeleteFilter;
    @FXML
    private TableColumn<Juego,String> titulo;
    @FXML
    private TableColumn<Juego,String> idJugador;
    @FXML
    private TableColumn<Juego,String> desarrolladora;

    private final ListViewModel listViewModel;

    @Inject
    public ListController(ListViewModel listViewModel) {
        this.listViewModel = listViewModel;
    }


    @Override
    public void principalCargado() {
        super.principalCargado();

        tituloDelete.setCellValueFactory(new PropertyValueFactory<>(Constant.TITULO));
        pegiDelete.setCellValueFactory(new PropertyValueFactory<>(Constant.PEGI));
        desarolladoraDelete.setCellValueFactory(new PropertyValueFactory<>(Constant.DESARROLLADORA));

        idBorrar.setCellValueFactory(new PropertyValueFactory<>(Constant.JUEGO_ID));
        tituloBorrar.setCellValueFactory(new PropertyValueFactory<>(Constant.TITULO));


        titulo.setCellValueFactory(new PropertyValueFactory<>(Constant.TITULO));
        idJugador.setCellValueFactory(new PropertyValueFactory<>(Constant.JUGADOR_ID));
        desarrolladora.setCellValueFactory(new PropertyValueFactory<>(Constant.DESARROLLADORA));

        listViewModel.loadList();
        listViewModel.getState().addListener((observableValue, loginState, newstate) -> Platform.runLater(() -> {
            if (newstate.getJuegosBorrarMultiple()!=null){
                tableDeleteMultiple.getItems().clear();
                tableDeleteMultiple.getItems().addAll(newstate.getJuegosBorrarMultiple());
            }
            if (newstate.getJuegosBorrarFiltro()!=null){
                tableDeleteFilter.getItems().clear();
                tableDeleteFilter.getItems().addAll(newstate.getJuegosBorrarFiltro());
            }

        }));



    }
    @FXML
    private void click() {
       if (filterCompany.getText()!=null){
           listViewModel.cargarListPorFilter( filterCompany.getText());
       }

    }
    @FXML
    private void addItemToDeleteMultiple() {
        if(tableDeleteMultiple.getSelectionModel().getSelectedItem()==null){
            mensajeLabel.setText(Constant.SELECCIONA_UN_ITEM);
        }else {
            tableToDelete.getItems().add(tableDeleteMultiple.getSelectionModel().getSelectedItem());
        }


    }

    public void removeMultiple() {
        List<String>idJuegos = new ArrayList<>();
       tableToDelete.getItems().forEach(id-> idJuegos.add(id.getJuegoId()));

       listViewModel.deleteMultiple(idJuegos);

    }
    @FXML
    private void deleteItemFilter() {
        listViewModel.deleteJuegosDeCompany(filterCompany.getText());

    }
}
