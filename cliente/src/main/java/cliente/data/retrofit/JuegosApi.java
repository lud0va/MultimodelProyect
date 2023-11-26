package cliente.data.retrofit;

import io.reactivex.rxjava3.core.Single;

import model.Juego;
import retrofit2.http.GET;
import retrofit2.http.Query;

import java.util.List;

public interface JuegosApi {
    @GET
    Single<List<Juego>> getJuegos();
    @GET("/{jugadorId}")

     List<Juego> getJuegosById(@Query("jugadorId") int id);
}
