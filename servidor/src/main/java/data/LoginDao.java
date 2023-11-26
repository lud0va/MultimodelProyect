package data;

import errores.ApiError;
import io.vavr.control.Either;
import model.Cuenta;

public interface LoginDao {

    Either<ApiError,Boolean> doLogin(String usuario,String passwrd);
}
