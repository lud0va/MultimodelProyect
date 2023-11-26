package cliente.ui.pantallas.principal;


import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import model.Cuenta;

import cliente.ui.common.BaseScreenController;
import cliente.ui.common.Screens;


import java.io.IOException;
import java.util.Optional;


public class PrincipalController extends BaseScreenController {


    Instance<Object> instance;

    @FXML
    private MenuBar menuPrinc;
    private Stage primaryStage;
    @Getter
    private Cuenta cuenta;


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
            case "customerAdd":
                loadScreen(Screens.CUSTOMERADD);
                break;
            case "customerDelete":
                loadScreen(Screens.CUSTOMERDELETE);
                break;
            case "customerModify":
                loadScreen(Screens.CUSTOMERMODIFY);
                break;
            case "costumerList":
                loadScreen(Screens.CUSTOMERLIST);
                break;
            case "menuItemLogout":
                logout();
                break;
            case "orderAdd":
                loadScreen(Screens.ORDERADD);
                break;
            case "orderDelete":
                loadScreen(Screens.ORDERDELETE);
                break;
            case "orderModify":
                loadScreen(Screens.ORDERMODIFY);
                break;
            case "orderList":
                loadScreen(Screens.ORDERLIST);
                break;

        }


    }


    public void onLogin(Cuenta cuenta) {

        this.cuenta = cuenta;
        menuPrinc.setVisible(true);
        loadScreen(Screens.CUSTOMERMAIN);

    }

}
