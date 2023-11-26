package services;

import data.JugadoresDao;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Jugador;

public class JugadorServices {

    private final JugadoresDao dao;

    @Inject
    public JugadorServices(JugadoresDao dao) {
        this.dao = dao;
    }

    public Either<ApiError, Jugador> addJugador(Jugador jugador){
        return dao.addJugador(jugador);

    }
}
