package jakarta.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Cuenta;
import model.Juego;

import java.util.List;

@Path("/cuenta")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestJuegos {
    @GET
    public List<Juego> getAllJuegos(){return null;}

    @GET
    @Path("/{jugadorId}")
    public Cuenta getCuentaById(@PathParam("jugadorId") int id){return null;}
}
