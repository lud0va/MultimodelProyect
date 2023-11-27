package cliente.domain.useCases.useCases;

import cliente.data.JuegoDao;
import cliente.domain.errores.ErrorClient;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Juego;

import java.util.List;

public class JuegoUseCaseImpl implements JuegoUseCase{
    private final JuegoDao dao;
   @Inject
    public JuegoUseCaseImpl(JuegoDao dao) {
        this.dao = dao;
    }

    @Override
    public Single<Either<ErrorClient, Juego>> addJuego(Juego juego) {
        return dao.addJuego(juego);
    }

    @Override
    public Single<Either<ErrorClient, List<Juego>>> getJuegos() {
        return dao.getJuegos();
    }

    @Override
    public Single<Either<ErrorClient, List<Juego>>> getJuegosDeCompany(String companyName) {
        return dao.getJuegosDeCompany(companyName);
    }

    @Override
    public Single<Either<ErrorClient, String>> deleteJuego(String id) {
        return dao.deleteJuego(id);
    }

    @Override
    public Single<Either<ErrorClient, String>> deleteJuegoMultiple(List<String> j) {
        return dao.deleteJuegoMultiple(j);
    }

    @Override
    public Single<Either<ErrorClient, String>> deleteJuegosDeCompany(String company) {
        return dao.deleteJuegosDeCompany(company);
    }

    @Override
    public Single<Either<ErrorClient, Juego>> updateJuego(Juego updatedJuego) {
        return dao.updateJuego(updatedJuego);
    }
}
