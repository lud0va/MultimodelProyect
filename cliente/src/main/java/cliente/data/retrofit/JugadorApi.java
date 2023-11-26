package cliente.data.retrofit;

import io.reactivex.rxjava3.core.Single;
import model.Cuenta;
import model.Jugador;
import retrofit2.http.POST;

public interface JugadorApi {
    @POST
    Single<Jugador> addJugador(Jugador jugador);
}
