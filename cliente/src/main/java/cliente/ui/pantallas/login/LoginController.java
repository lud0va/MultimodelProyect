package cliente.ui.pantallas.login;

import jakarta.inject.Inject;
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

    @Inject
    public LoginController(LoginViewModel loginViewModel) {
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void principalCargado() {
        super.principalCargado();
    }

   @FXML
    private void Sign(ActionEvent actionEvent) {


    }
    @FXML
    private void login(ActionEvent actionEvent) {
        loginViewModel.doLogin(userLog.getText(),passLog.getText());
        userERR.setText(loginViewModel.getState().get().getLogged().toString());
    }
}
