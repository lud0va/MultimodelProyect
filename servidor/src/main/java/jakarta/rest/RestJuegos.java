package jakarta.rest;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public List<Juego> getJuegosById(@PathParam("jugadorId") int id){return null;}
    @POST
    public Response addJuego(Juego juego) {return null;}
    @PUT
    @Path("/{juegoId}")
    public Response updateJuego(@PathParam("juegoId") int id, Juego updatedJuego) {
       return null;
    }

    @DELETE
    @Path("/{juegoId}")
    public Response deleteJuego(@PathParam("juegoId") int id) {
     return null;
    }
}

