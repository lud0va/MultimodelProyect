package cliente.data;

import cliente.data.retrofit.CuentaApi;
import com.google.gson.Gson;
import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Cuenta;

import java.time.LocalDateTime;

public class CuentaDaoImpl extends DaoGenerics  implements CuentaDao{


    private final CuentaApi cuentaApi;


    @Inject
    public CuentaDaoImpl(CuentaApi cuentaApi, Gson gson) {
        super(gson);

        this.cuentaApi = cuentaApi;
    }
    public Single<Either<ApiError, Cuenta>> getUsuarioById(String id){
        return safeSingleApicall(cuentaApi.getCuentaById(id))
                  .subscribeOn(Schedulers.io());


    }

    @Override
    public Single<Either<ApiError, Cuenta>> addCuenta(Cuenta cuenta) {
        return safeSingleApicall(cuentaApi.addCuenta(cuenta).subscribeOn(Schedulers.io()));
    }

    @Override
        public Single<Either<ApiError, Boolean>> doLogin(String user, String passwd) {

        return safeSingleApiCall(cuentaApi.doLogin(user,passwd).subscribeOn(Schedulers.io()));
    }
}
