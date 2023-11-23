package jakarta.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Cuenta;
import services.CuentasServices;

@Path("/cuenta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCuenta {
    private final CuentasServices serv;

    @Inject
    public RestCuenta(CuentasServices serv) {
        this.serv = serv;
    }

    @GET
    @Path("/{id}")
    public Cuenta getCuentaById(@PathParam("id") String id) {
        return serv.getCuenta(id).get();
    }

    @POST
    public Response addCuenta(Cuenta cuenta) {
        return null;
    }
}
