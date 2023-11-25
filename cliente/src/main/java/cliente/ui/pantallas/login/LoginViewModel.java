package cliente.ui.pantallas.login;

import cliente.data.CuentaDao;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class LoginViewModel {

    private final CuentaDao dao;

    public LoginViewModel(CuentaDao dao) {
        this.dao = dao;
        this.state = new SimpleObjectProperty<>(new LoginState());
    }

    private final ObjectProperty<LoginState> state;
    public ReadOnlyObjectProperty<LoginState> getState() {
        return state;
    }












}
