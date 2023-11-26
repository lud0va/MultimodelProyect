package cliente.domain.useCases.useCases;

import cliente.domain.errores.ErrorClient;
import io.reactivex.rxjava3.core.Single;
import io.vavr.control.Either;
import model.Juego;

import java.util.List;

public interface JuegoUseCase {



    Single<Either<ErrorClient, Juego>> addJuego(Juego juego);

    Single<Either<ErrorClient, List<Juego>>> getJuegos();
    public Single<Either<ErrorClient,List<Juego>>> getJuegosDeCompany( String companyName);
    public Single<Either<ErrorClient,String>> deleteJuego(String id);
    Single<Either<ErrorClient,String>> deleteJuegoMultiple(List<String> j);

    public  Single<Either<ErrorClient,String>> deleteJuegosDeCompany(String company);

    public Single<Either<ErrorClient,Juego>> updateJuego( Juego updatedJuego);
}
