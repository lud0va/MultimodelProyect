package cliente.data.retrofit;

import io.reactivex.rxjava3.core.Single;

import model.Juego;
import model.Jugador;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface JuegosApi {
    @GET("juegos")
    Single<List<Juego>> getJuegos();
    @GET("/{jugadorId}")
     Single<List<Juego>> getJuegosById(@Query("jugadorId") int id);

    @GET("juegos/juegosDeCompany")
    Single<List<Juego>>  getJuegosDeCompany(@Query ("company") String companyName);

    @DELETE("cuenta/{juegoId}")
    Single<Response<Void>> deleteJuego(@Path("juegoId") String id);
    @DELETE("juegos/juegosDeCompany")
    Single<Response<Void>> deleteJuegosDeCompany(@Query("company")String company);

    @DELETE("juegos/deleteMultiple")
    Single<Response<Void>> deleteJuegoMultiple(@Query("juegoIds")List<String> j);

    @PUT("juegos/{juegoId}")
   Single<Juego> updateJuego(Juego updatedJuego);

    @POST("juegos/")
    Single<Juego>addJuego(Juego juego);
}
