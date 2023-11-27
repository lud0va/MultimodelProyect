package cliente.data.retrofit;

import common.Constant;
import io.reactivex.rxjava3.core.Single;

import model.Juego;
import model.Jugador;
import retrofit2.Response;
import retrofit2.http.*;

import java.util.List;

public interface JuegosApi {

    @GET(Constant.JUEGOS)
    Single<List<Juego>> getJuegos();

    @GET(Constant.JUEGOS_JUEGOS_DE_COMPANY)
    Single<List<Juego>>  getJuegosDeCompany(@Query (Constant.COMPANY) String companyName);

    @DELETE(Constant.CUENTA_JUEGO_ID_PATH)
    Single<Response<Void>> deleteJuego(@Path(Constant.JUEGO_ID) String id);
    @DELETE(Constant.JUEGOS_JUEGOS_DE_COMPANY)
    Single<Response<Void>> deleteJuegosDeCompany(@Query(Constant.COMPANY)String company);

    @DELETE(Constant.JUEGOS_DELETE_MULTIPLE)
    Single<Response<Void>> deleteJuegoMultiple(@Query(Constant.JUEGO_IDS)List<String> j);

    @PUT(Constant.JUEGOS_JUEGO_ID_PATH)
   Single<Juego> updateJuego(Juego updatedJuego);

    @POST(Constant.JUEGOS_PATH)
    Single<Juego>addJuego(Juego juego);
}
