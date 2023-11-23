package ui.principal;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Data;
import model.Cuenta;
import ui.common.BaseScreenController;

@Data
public class LoginController extends BaseScreenController {

    @FXML
    private Label userERR;
    @FXML
    private TextField userLog;
    @FXML
    private TextField passLog;

  /*  private final CredentialService se;

    @Inject
    public LoginController(CredentialService se) {
        this.se = se;
    }*/


    @FXML
    private void login(ActionEvent actionEvent) {


    }
}
