package cliente.useCases;


import cliente.data.CuentaDao;
import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;

public class CuentaUseCaseImpl implements CuentaUseCase {
    private CuentaDao dao;

    @Inject
    public CuentaUseCaseImpl(CuentaDao dao) {
        this.dao = dao;
    }

    public Single<Either<ApiError, Boolean>> doLogin(String username, String passwrd) {
        return dao.doLogin(username, passwrd);}
}
