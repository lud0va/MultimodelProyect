package cliente.data.retrofit;

import io.reactivex.rxjava3.core.Single;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import model.Cuenta;

public interface CuentaApi {

    @GET
    @Path("/{id}")
    Single<Cuenta> getCuentaById(@PathParam("id") String id);

    @POST
     Single<Response> addCuenta(Cuenta cuenta);
}
