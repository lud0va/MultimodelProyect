package cliente.ui.pantallas.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Data;
import cliente.ui.common.BaseScreenController;

@Data
public class LoginController extends BaseScreenController {

    private  LoginViewModel loginViewModel;
    @FXML
    private Label userERR;
    @FXML
    private TextField userLog;
    @FXML
    private TextField passLog;

    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @FXML
    private void login(ActionEvent actionEvent) {


    }
}
