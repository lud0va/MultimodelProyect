package cliente.domain.usecases;

import cliente.domain.errores.ErrorClient;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Jugador;

public interface JugadorUseCase {


    public Single<Either<ErrorClient, Jugador>> addJugador(Jugador jugador);
}
