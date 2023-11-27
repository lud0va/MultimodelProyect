package cliente.data.retrofit;

import common.Constant;
import io.reactivex.rxjava3.core.Single;

import model.Cuenta;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CuentaApi {


    @GET(Constant.CUENTA_LOGIN_GET)
    Single<Boolean> doLogin(@Query(Constant.NOMBRE_USUARIO_QUERY) String user, @Query(Constant.PASSWORD_QUERY) String password);
    @POST(Constant.CUENTA_PATH)
     Single<Cuenta> addCuenta(Cuenta cuenta);
}
