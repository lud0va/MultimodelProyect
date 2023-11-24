package dao.retrofit;

import io.reactivex.rxjava3.core.Single;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Path;
import model.Juego;
import retrofit2.http.GET;

import java.util.List;

public interface JuegosApi {
    @GET
    Single<List<Juego>> getJuegos();
    @GET
    @Path("/{jugadorId}")
     List<Juego> getJuegosById(@PathParam("jugadorId") int id);
}
