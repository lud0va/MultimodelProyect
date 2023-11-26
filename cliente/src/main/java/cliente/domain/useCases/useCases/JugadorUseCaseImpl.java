package cliente.domain.useCases.useCases;

import cliente.data.JugadorDao;
import cliente.domain.errores.ErrorClient;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Jugador;

public class JugadorUseCaseImpl implements JugadorUseCase{

    private final JugadorDao dao;

    @Inject
    public JugadorUseCaseImpl(JugadorDao dao) {
        this.dao = dao;
    }

    @Override
    public Single<Either<ErrorClient, Jugador>> addJugador(Jugador jugador) {
        return dao.addJugador(jugador);
    }
}
