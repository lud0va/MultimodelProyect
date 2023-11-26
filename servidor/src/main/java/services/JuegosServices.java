package services;

import data.JuegoDao;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Juego;

import java.util.Iterator;
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

    public Either<ApiError,Juego>addJuego(Juego j){
        return dao.addJuego(j);
    }

    public Either<ApiError,Juego> updateJuego(  Juego updatedJuego){
        return dao.update(updatedJuego);
    }
    public Either<ApiError,List<Juego>> getJuegosDeCompany(String companyName){
        return dao.getJuegosDeCompany(companyName);
    }

    public Either<ApiError,Boolean>delete(String id){
      return dao.deleteJuego(id);
    }

    public Either<ApiError,Boolean>deleteJuegosDeCompany(String companyName){
        return dao.deleteJuegosDeCompany(companyName);
    }

    public Either<ApiError, Integer>deleteListaJuegos(List<String> ids){
        return dao.deleteListJuegos(ids);
    }

}
