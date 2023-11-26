package cliente.domain.useCases.useCases;

import cliente.common.ResultMio;
import cliente.domain.errores.ErrorClient;
import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

public interface CuentaUseCase {

    Single<Either<ErrorClient, Boolean>> doLogin(String username, String passwrd);

}
