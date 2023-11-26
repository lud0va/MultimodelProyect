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

    private final CuentasServices serv;
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

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
    public Boolean getLoginGet(@QueryParam("nombreUsuario") String user, @QueryParam ("password") String password) {
       // request.getSession().setAttribute("LOGIN", true);
        return serv.doLogin(user,password).get();
    }

    @POST
    public Cuenta addCuenta(Cuenta cuenta) {
        return serv.addCuenta(cuenta).get();
    }
}
