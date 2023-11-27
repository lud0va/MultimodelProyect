package cliente.ui.pantallas.list;

import cliente.ui.common.BaseScreenController;
import jakarta.inject.Inject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Juego;

public class ListController extends BaseScreenController {
    @FXML
    private TableView<Juego> tableDeleteMultiple;
    @FXML
    private TableColumn<Juego,String> tituloDelete;
    @FXML
    private TableColumn<Juego,String> pegiDelete;
    @FXML
    private TableColumn<Juego,String> desarolladoraDelete;
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
    private TableColumn<Juego,String> id;

    private final ListViewModel listViewModel;

    @Inject
    public ListController(ListViewModel listViewModel) {
        this.listViewModel = listViewModel;
    }


    @Override
    public void principalCargado() {
        super.principalCargado();
        tituloDelete.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        pegiDelete.setCellValueFactory(new PropertyValueFactory<>("pegi"));
        desarolladoraDelete.setCellValueFactory(new PropertyValueFactory<>("desarrolladora"));


    }
}
