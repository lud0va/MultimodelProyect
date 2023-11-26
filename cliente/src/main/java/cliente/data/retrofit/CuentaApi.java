package cliente.data.retrofit;

import io.reactivex.rxjava3.core.Single;

import model.Cuenta;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CuentaApi {

    @GET("cuenta/{id}")
    Single<Cuenta> getCuentaById(@Path("id") String id);

    @GET("cuenta/login")
    Single<Boolean> doLogin(@Query("nombreUsuario") String user, @Query("password") String password);
    @POST
     Single<Cuenta> addCuenta(Cuenta cuenta);
}
