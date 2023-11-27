package cliente.ui.pantallas.add;

import cliente.ui.common.BaseScreenController;
import common.Constant;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Juego;

public class AddController extends BaseScreenController {
    @FXML
    private Label mensajeLabel;
    @FXML
    private TableColumn<Juego,String> titulo;
    @FXML
    private  TableColumn<Juego,String> desarrolladora;
    @FXML
    private  TableColumn<Juego,String> pegi;

    @FXML
    private TextField tituloText;
    @FXML
    private TextField desarrolladoraText;
    @FXML
    private TextField pegiText;
    @FXML
    private TextField precioText;
    @FXML
    private TableView<Juego> juegos;
    private final AddViewModel addViewModel;

    public AddController(AddViewModel addViewModel) {
        this.addViewModel = addViewModel;
    }

    @Override
    public void principalCargado() {
        titulo.setCellValueFactory(new PropertyValueFactory<>(Constant.TITULO));
        pegi.setCellValueFactory(new PropertyValueFactory<>(Constant.PEGI));
        desarrolladora.setCellValueFactory(new PropertyValueFactory<>(Constant.DESARROLLADORA));

         addViewModel.loadList();
        addViewModel.getState().addListener((observableValue, loginState, newstate) -> Platform.runLater(() -> {

            if (newstate.getJuegos()!=null){
                juegos.getItems().clear();
                juegos.getItems().addAll(newstate.getJuegos());
            }

        }));
    }
    @FXML
    private void addJuego() {
        if (tituloText.getText()==null || pegiText.getText()==null || desarrolladoraText.getText()==null || pegi.getText()==null || precioText.getText()==null){
            mensajeLabel.setText(Constant.INTRODUCE_TODOS_LOS_ELEMENTOS);
        }else {
            addViewModel.addJuego(new Juego(tituloText.getText(),Integer.parseInt(pegiText.getText()),Double.parseDouble(precioText.getText()),desarrolladoraText.getText()));
        }


    }
    @FXML
    private void updateJuego() {
        if (tituloText.getText()==null || pegiText.getText()==null || desarrolladoraText.getText()==null || pegi.getText()==null || precioText.getText()==null){
            mensajeLabel.setText(Constant.INTRODUCE_TODOS_LOS_ELEMENTOS);
        }else {
            addViewModel.updateJuego(new Juego(tituloText.getText(),Integer.parseInt(pegiText.getText()),Double.parseDouble(precioText.getText()),desarrolladoraText.getText()));
        }
    }

    public void clickTable() {
        if (juegos.getSelectionModel().getSelectedItem()!=null){
            Juego j=juegos.getSelectionModel().getSelectedItem();
            tituloText.setText(j.getTituloJuego());
            pegiText.setText(String.valueOf(j.getPegi()));
            precioText.setText(String.valueOf(j.getPrecio()));
            desarrolladoraText.setText(j.getDesarrolladora());
        }


    }
}
