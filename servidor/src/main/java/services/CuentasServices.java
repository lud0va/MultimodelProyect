package services;

import data.CuentaDao;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Cuenta;

public class CuentasServices {


    private final CuentaDao dao;

    @Inject

    public CuentasServices(CuentaDao dao) {
        this.dao = dao;
    }

    public Either<ApiError, Cuenta>getCuenta(String idcuenta){
        return dao.getCuenta(idcuenta);
    }

    public Either<ApiError,Cuenta>getCuentaToLogin(String usuario,String passw){
        return dao.getCuentaToLogin(usuario,passw);

    }
}
