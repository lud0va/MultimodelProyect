package data;

import errores.ApiError;
import io.vavr.control.Either;
import model.Cuenta;

public interface CuentaDao {


    Either<ApiError,Cuenta> getCuenta(String idcuenta);
    Either<ApiError,Cuenta> addCuenta(Cuenta cuenta);
    Either<ApiError,Boolean> doLogin(String usuario,String passwrd);
}
