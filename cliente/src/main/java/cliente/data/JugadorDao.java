package cliente.data;

import cliente.data.retrofit.JugadorApi;
import cliente.domain.errores.ErrorClient;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Jugador;

public class JugadorDao  extends DaoGenerics{

   private final JugadorApi jugadorApi;
    @Inject
    public JugadorDao(Gson gson, JugadorApi jugadorApi) {
        super(gson);
        this.jugadorApi = jugadorApi;
    }

    public Single<Either<ErrorClient, Jugador>> addJugador(Jugador jugador) {
        return safeSingleApicall(jugadorApi.addJugador(jugador).subscribeOn(Schedulers.io()));
    }

}
