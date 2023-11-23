package dao;

import errores.ApiError;
import io.vavr.control.Either;
import jakarta.ws.rs.PathParam;
import model.Juego;

import java.util.List;

public interface JuegoDao {


     Either<ApiError,List<Juego>> getAllJuegos();

     Either<ApiError,List<Juego>> getJuegosDeJugador(int id);

}
