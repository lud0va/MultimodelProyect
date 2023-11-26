package cliente.ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import model.Cuenta;

import cliente.ui.common.BaseScreenController;
import cliente.ui.common.Screens;


import java.io.IOException;


public class PrincipalController extends BaseScreenController {


    Instance<Object> instance;

    @FXML
    private MenuBar menuPrinc;
    private Stage primaryStage;



    @FXML
    public BorderPane root;


    private final Alert alert;


    @Inject
    public PrincipalController(Instance<Object> instance) {

        this.instance = instance;
        alert = new Alert(Alert.AlertType.NONE);


    }

    private void loadScreen(Screens pantalla) {


        changeScreen(loadScreen(pantalla.getRuta()));

    }


    private Pane loadScreen(String ruta) {
        Pane panePantalla = null;
        try {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setControllerFactory(controller -> instance.select(controller).get());
            panePantalla = fxmlLoader.load(getClass().getResourceAsStream(ruta));
            BaseScreenController pantallaController = fxmlLoader.getController();
            pantallaController.setPrincipalController(this);

            pantallaController.principalCargado();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return panePantalla;
    }


    public void logout() {

        menuPrinc.setVisible(false);
        loadScreen(Screens.LOGIN);
    }

    private void changeScreen(Pane newScreen) {


        root.setCenter(newScreen);
    }


    public void initialize() {
        menuPrinc.setVisible(false);
        loadScreen(Screens.LOGIN);

    }






    public void setStage(Stage stage) {
        primaryStage = stage;

    }


    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case "add":
                loadScreen(Screens.ADD);
                break;
            case "listar":
                loadScreen(Screens.LIST);
                break;


        }


    }


    public void onLogin() {


        menuPrinc.setVisible(true);
        loadScreen(Screens.LIST);

    }

}
