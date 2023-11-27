package cliente.domain.usecases;

import cliente.data.JugadorDao;
import cliente.domain.errores.ErrorClient;
import common.Constant;
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

        if (Boolean.FALSE.equals(validateUser(jugador))){
            return Single.just(Either.left(new ErrorClient(Constant.EL_JUGADOR_TIENE_QUE_TENER_MAS_DE_18)));
        }
        return dao.addJugador(jugador);
    }


    private Boolean validateUser(Jugador jugador) {
        return jugador.getEdad() >= 18;
    }






}
