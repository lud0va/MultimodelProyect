package data;

import errores.ApiError;
import io.vavr.control.Either;
import model.Cuenta;

public interface CuentaDao {

    Either<ApiError,Cuenta>getCuentaToLogin(String usuario,String passw);
    Either<ApiError,Cuenta> getCuenta(String idcuenta);
    Either<ApiError,Cuenta> addCuenta(Cuenta cuenta);
}
