package cliente.data;

import cliente.data.retrofit.CuentaApi;
import cliente.data.retrofit.JuegosApi;
import cliente.domain.errores.ErrorClient;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Cuenta;


public class CuentaDao extends DaoGenerics {


    private final CuentaApi cuentaApi;



    @Inject
    public CuentaDao(CuentaApi cuentaApi, Gson gson, JuegosApi juegosApi) {
        super(gson);

        this.cuentaApi = cuentaApi;


    }
    public Single<Either<ErrorClient, Cuenta>> getUsuarioById(String id){
        return safeSingleApicall(cuentaApi.getCuentaById(id))
                  .subscribeOn(Schedulers.io());


    }


    public Single<Either<ErrorClient, Cuenta>> addCuenta(Cuenta cuenta) {
        return safeSingleApicall(cuentaApi.addCuenta(cuenta).subscribeOn(Schedulers.io()));
    }


        public Single<Either<ErrorClient, Boolean>> doLogin(String user, String passwd) {

        return safeSingleApicall(cuentaApi.doLogin(user,passwd).subscribeOn(Schedulers.io())) ;
    }
}
