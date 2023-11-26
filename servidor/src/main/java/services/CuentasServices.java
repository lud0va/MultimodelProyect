package services;

import data.CuentaDao;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import model.Cuenta;

public class CuentasServices {
    private final Pbkdf2PasswordHash passwordHash;

    private SecurityContext securityContext;

    private final CuentaDao dao;

    @Inject
    public CuentasServices(CuentaDao dao,Pbkdf2PasswordHash passwordHash) {
        this.dao = dao;
        this.passwordHash=passwordHash;
    }

    public Either<ApiError, Cuenta>getCuenta(String idcuenta){
        Either<ApiError, Cuenta> cuenta=dao.getCuenta(idcuenta);
        if (cuenta!=null){
            String password = passwordHash.generate("1234".toCharArray());
            cuenta.get().setPassword(password);
        }

        return cuenta;


    }
    public  Either<ApiError,Cuenta>addCuenta(Cuenta cuenta){



        return dao.addCuenta(cuenta);
    }



    public Either<ApiError,Boolean> doLogin(String usuario,String passw){
        return dao.doLogin(usuario,passw);

    }
}
