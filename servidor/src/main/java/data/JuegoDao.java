package data;

import errores.ApiError;
import io.vavr.control.Either;
import model.Juego;

import java.util.List;

public interface JuegoDao {


     Either<ApiError,List<Juego>> getAllJuegos();

     Either<ApiError, List<Juego>> getJuegosDeJugador(String id);

     Either<ApiError,List<Juego>>getJuegosDeCompany(String companyNombre);

     Either<ApiError,Boolean> deleteJuego(String id);

     Either<ApiError,Juego> addJuego(Juego j);

     Either<ApiError,Juego> update(Juego j);

     Either<ApiError,Boolean>deleteJuegosDeCompany(String companyNombre);

     Either<ApiError,Integer>deleteListJuegos(List<String>juegos);



}
