package services;

import data.JuegoDao;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Juego;

import java.util.List;

public class JuegosServices {

    private final JuegoDao dao;
    @Inject

    public JuegosServices(JuegoDao dao) {
        this.dao = dao;
    }

    public Either<ApiError, List<Juego>> getAllJuego(){
        return dao.getAllJuegos();
    }

    public Either<ApiError, List<Juego>> getJuegosDeJug(String id){


        return dao.getJuegosDeJugador(id);}

}
