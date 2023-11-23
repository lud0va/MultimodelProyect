package dao;

import errores.ApiError;
import io.vavr.control.Either;
import model.Cuenta;

public interface CuentaDao {


    Either<ApiError,Cuenta> getCuenta(int idcuenta);
    Cuenta addCuenta(Cuenta cuenta);
}
