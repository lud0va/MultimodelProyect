package cliente.ui.pantallas.principal;


import cliente.ui.common.error.FxExceptions;
import common.Constant;
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
import javafx.stage.WindowEvent;
import lombok.Getter;
import model.Cuenta;

import cliente.ui.common.BaseScreenController;
import cliente.ui.common.Screens;


import java.io.IOException;


public class PrincipalController extends BaseScreenController {


    Instance<Object> instance;

    @FXML
    private MenuBar menuPrinc;


    @FXML
    public BorderPane root;

    private Stage primaryStage;



    @Inject
    public PrincipalController(Instance<Object> instance) {

        this.instance = instance;



    }

    public void setStage(Stage stage) {
        primaryStage = stage;

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
            throw new FxExceptions(e.getMessage());
        }
        return panePantalla;
    }




    private void changeScreen(Pane newScreen) {


        root.setCenter(newScreen);
    }


    public void initialize() {
        menuPrinc.setVisible(false);
        loadScreen(Screens.LOGIN);

    }









    @FXML
    private void menuClick(ActionEvent actionEvent) {
        switch (((MenuItem) actionEvent.getSource()).getId()) {
            case Constant.ADD_MENU:
                loadScreen(Screens.ADD);
                break;
            case Constant.LISTAR_MENU:
                loadScreen(Screens.LIST);
                break;

        }


    }


    public void onLogin() {


        menuPrinc.setVisible(true);
        loadScreen(Screens.LIST);

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
