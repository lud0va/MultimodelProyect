package cliente.ui.pantallas.login;

import lombok.Data;
import model.Cuenta;

@Data

public class LoginState {
    private final Cuenta cuenta;
    private final Boolean logged;

    private final String error;


}
