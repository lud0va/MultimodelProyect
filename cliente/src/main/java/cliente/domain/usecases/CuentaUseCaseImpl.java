package cliente.domain.usecases;


import cliente.data.CuentaDao;
import cliente.domain.errores.ErrorClient;
import common.Constant;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Cuenta;

public class CuentaUseCaseImpl implements CuentaUseCase {
    private CuentaDao dao;

    @Inject
    public CuentaUseCaseImpl(CuentaDao dao) {
        this.dao = dao;
    }

    public Single<Either<ErrorClient, Boolean>> doLogin(String username, String passwrd) {
        return dao.doLogin(username, passwrd);
    }

    public Single<Either<ErrorClient, Cuenta>> addCuenta(Cuenta cuenta) {
        if (Boolean.FALSE.equals(validateEmail(cuenta))) {
            return Single.just(Either.left(new ErrorClient(Constant.EMAIL_INVALIDO)));
        }
        if (Boolean.FALSE.equals(validatePassword(cuenta))) {
            return Single.just(Either.left(new ErrorClient(Constant.PASSWD_INVALIDA)));

        }

        return dao.addCuenta(cuenta);

    }


    private Boolean validateEmail(Cuenta usuario) {
        return usuario.getCorreoElectronico() != null && usuario.getCorreoElectronico().contains(Constant.ARROBA_CONTIENE);
    }


    private Boolean validatePassword(Cuenta usuario) {
        return !usuario.getPassword().matches(Constant.A_Z) && usuario.getPassword().length() >= 10;
    }
}
