package cliente.data;

import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Cuenta;

public interface CuentaDao {

    public Single<Either<ApiError, Cuenta>> getUsuarioById(String id);
}
