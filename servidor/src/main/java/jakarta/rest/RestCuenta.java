package jakarta.rest;

import config.ConstantServer;
import jakarta.inject.Inject;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.SecurityContext;
import model.Cuenta;
import services.CuentasServices;



@Path(ConstantServer.CUENTA_PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestCuenta {

    private final CuentasServices serv;
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;

    @Context
    private SecurityContext securityContext;

    @Inject
    public RestCuenta(CuentasServices serv) {
        this.serv = serv;
    }

    @GET
    @Path(ConstantServer.ID_PATH_GET)
    public Cuenta getCuentaById(@PathParam(ConstantServer.ID) String id) {
        return serv.getCuenta(id).get();
    }

    @GET
    @Path(ConstantServer.LOGIN_PATH)
    public Boolean doLogin(@QueryParam(ConstantServer.NOMBRE_USUARIO) String user, @QueryParam(ConstantServer.PASSWORD) String password) {


        if (Boolean.TRUE.equals(serv.doLogin(user, password).get())) {
            request.getSession().setAttribute(ConstantServer.LOGIN, true);
            return true;
        }else {
            return false;
        }


    }

    @POST
    public Cuenta addCuenta(Cuenta cuenta) {
        return serv.addCuenta(cuenta).get();
    }
}
