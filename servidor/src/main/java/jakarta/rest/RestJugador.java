package jakarta.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Juego;
import model.Jugador;

import services.JugadorServices;

import java.util.List;

@Path("/jugador")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestJugador {

    private final JugadorServices serv;

    @Inject
    public RestJugador(JugadorServices serv) {
        this.serv = serv;
    }
    @POST
    public Jugador addJugador(Jugador jugador){
        return serv.addJugador(jugador).get();
    }
}
