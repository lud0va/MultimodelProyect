package cliente.domain.usecases;

import cliente.domain.errores.ErrorClient;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Cuenta;

public interface CuentaUseCase {

    Single<Either<ErrorClient, Boolean>> doLogin(String username, String passwrd);

    public Single<Either<ErrorClient, Cuenta>>addCuenta(Cuenta cuenta);
}
