package cliente.ui.main;

import cliente.ui.pantallas.principal.PrincipalController;
import common.Constant;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

public class MainFX {

    @Inject
    FXMLLoader fxmlLoader;

    public void start(@Observes @StartupScene Stage stage) {
        try {

            Parent fxmlParent = fxmlLoader.load(getClass().getResourceAsStream(Constant.FXML_PRINCIPAL_SCREEN_FXML));
            PrincipalController controller = fxmlLoader.getController();
            controller.setStage(stage);

            stage.setScene(new Scene(fxmlParent));

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
