package jakarta.rest;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Cuenta;
import services.CuentasServices;

@Path("/cuenta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCuenta {
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;
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
    @GET
    @Path("/login")
    public Response getLoginGet(@QueryParam("user") String user, @QueryParam ("password") String password) {
        request.getSession().setAttribute("LOGIN", null);
        if (user == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (!user.equals("admin") || !password.equals("admin"))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        request.getSession().setAttribute("LOGIN", true);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    public Response addCuenta(Cuenta cuenta) {
        return null;
    }
}
