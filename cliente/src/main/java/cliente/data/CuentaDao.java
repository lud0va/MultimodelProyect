package cliente.data;

import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Cuenta;

public interface CuentaDao {

     Single<Either<ApiError, Cuenta>> getUsuarioById(String id);

     Single<Either<ApiError, Cuenta>> addCuenta(Cuenta cuenta);

     Single<Either<ApiError, Boolean>> doLogin(String user, String passwd);
}
