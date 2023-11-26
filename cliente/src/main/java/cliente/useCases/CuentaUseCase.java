package cliente.useCases;

import cliente.common.ResultMio;
import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;

public interface CuentaUseCase {

    Single<Either<ApiError, Boolean>> doLogin(String username, String passwrd);

}
