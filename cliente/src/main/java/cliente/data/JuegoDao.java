package cliente.data;

import cliente.data.retrofit.JuegosApi;
import cliente.domain.errores.ErrorClient;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Juego;
import model.Jugador;
import retrofit2.Response;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public class JuegoDao extends DaoGenerics {

    private final JuegosApi juegosApi;

    @Inject
    public JuegoDao(Gson gson, JuegosApi juegosApi) {
        super(gson);
        this.juegosApi = juegosApi;
    }


    public Single<Either<ErrorClient, Juego>> addJuego(Juego game) {

        return safeSingleApicall(juegosApi.addJuego(game).subscribeOn(Schedulers.io()));
    }
    public  Single<Either<ErrorClient,List<Juego>>> getJuegos(){
        return safeSingleApicall(juegosApi.getJuegos().subscribeOn(Schedulers.io()));
    }
    public Single<Either<ErrorClient,List<Juego>>> getJuegosDeCompany( String companyName){
          return safeSingleApicall(juegosApi.getJuegosDeCompany(companyName).subscribeOn(Schedulers.io()));
    }
    public Single<Either<ErrorClient,String>> deleteJuego(String id){
        return safeSingleVoidApicall(juegosApi.deleteJuego(id).subscribeOn(Schedulers.io()));
    }
   public Single<Either<ErrorClient,String>> deleteJuegoMultiple(List<String> j){
      return safeSingleVoidApicall(juegosApi.deleteJuegoMultiple(j).subscribeOn(Schedulers.io()));
    }
    public  Single<Either<ErrorClient,String>> deleteJuegosDeCompany(String company){
       return safeSingleVoidApicall(juegosApi.deleteJuegosDeCompany(company).subscribeOn(Schedulers.io()));
    }
    public Single<Either<ErrorClient,Juego>> updateJuego( Juego updatedJuego){
       return safeSingleApicall(juegosApi.updateJuego(updatedJuego).subscribeOn(Schedulers.io()));

    }

}
