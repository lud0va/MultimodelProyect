package jakarta.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Juego;
import services.JuegosServices;

import java.util.List;

@Path("/juegos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestJuegos {
    private final JuegosServices serv;

    @Inject
    public RestJuegos(JuegosServices serv) {
        this.serv = serv;
    }

    @GET
    public List<Juego> getAllJuegos() {
        return serv.getAllJuego().get();
    }

    @GET
    @Path("/{jugadorId}")
    public List<Juego> getJuegosById(@PathParam("jugadorId") String id) {
        return serv.getJuegosDeJug(id).get();
    }

    @POST
    public Response addJuego(Juego juego) {
        return null;
    }

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

