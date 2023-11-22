package jakarta.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Cuenta;

@Path("/cuenta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCuenta {

    @GET
    @Path("/{id}")
    public Cuenta getCuentaById(@PathParam("id") int id){return null;}

    @POST
    public Response addCuenta(Cuenta cuenta){return null;}
}
