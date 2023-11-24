package cliente.data;

import cliente.data.retrofit.CuentaApi;
import com.google.gson.Gson;
import errores.ApiError;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import model.Cuenta;

public class CuentaDaoImpl extends DaoGenerics  implements CuentaDao{


    private final CuentaApi cuentaApi;


    public CuentaDaoImpl(CuentaApi cuentaApi, Gson gson) {
        super(gson);

        this.cuentaApi = cuentaApi;
    }
    public Single<Either<ApiError, Cuenta>> getUsuarioById(String id){
        return safeSingleApicall(cuentaApi.getCuentaById(id))
                  .subscribeOn(Schedulers.io());


    }
}
