package cliente.ui.pantallas.login;

import jakarta.inject.Inject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lombok.Data;
import cliente.ui.common.BaseScreenController;
import model.Cuenta;

@Data
public class LoginController extends BaseScreenController {

    @FXML
    private Button signButton;
    @FXML
    private TextField nombreSign;
    @FXML
    private TextField apellidoSign;
    @FXML
    private TextField usernameSign;
    @FXML
    private TextField passSign;
    @FXML
    private TextField ageSign;
    @FXML
    private TextField mailSign;
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
        loginViewModel.getState().addListener((observableValue, loginState, newstate) ->{
            if (newstate.getError()!=null){
                userERR.setText(newstate.getError());
            }
            if (newstate.getLogged()){
                this.getPrincipalController().onLogin();
            }
        });
    }

   @FXML
    private void Sign(ActionEvent actionEvent) {
      loginViewModel.doSign(nombreSign.getText(),apellidoSign.getText(),usernameSign.getText(),passSign.getText(),ageSign.getText(),mailSign.getText());

    }
    @FXML
    private void login(ActionEvent actionEvent) {
        loginViewModel.doLogin(userLog.getText(),passLog.getText());
        userERR.setText(loginViewModel.getState().get().getLogged().toString());
    }
}
