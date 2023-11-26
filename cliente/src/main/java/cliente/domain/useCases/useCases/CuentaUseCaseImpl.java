package cliente.domain.useCases.useCases;


import cliente.data.CuentaDao;
import cliente.domain.errores.ErrorClient;
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
        return dao.doLogin(username, passwrd);}

    public Single<Either<ErrorClient,Cuenta>>addCuenta(Cuenta cuenta){
       return dao.addCuenta(cuenta);

    }
}
