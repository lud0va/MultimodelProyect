package data;

import errores.ApiError;
import io.vavr.control.Either;

import model.Jugador;

public interface JugadoresDao {
    Either<ApiError, Jugador> addJugador(Jugador jugador);

}
