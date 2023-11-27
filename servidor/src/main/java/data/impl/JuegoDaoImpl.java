package data.impl;

import common.Constant;
import config.ConstantServer;
import data.JuegoDao;
import data.impl.commondatabase.DBConnection;
import data.impl.commondatabase.DBQueries;
import errores.ApiError;
import errores.exceptions.BDDException;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Juego;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class JuegoDaoImpl implements JuegoDao {
    private final DBConnection db;
    private ResultSet rs;

    @Inject
    public JuegoDaoImpl(DBConnection db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, List<Juego>> getAllJuegos() {
        List<Juego> juegos;
        try (Connection con = db.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            rs = statement.executeQuery(DBQueries.SELECT_JUEGO);

            juegos = readFile(rs);

        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }

        if (juegos.isEmpty()) {
            return Either.left(new ApiError(ConstantServer.ERROR_AL_CARGAR_LA_CUENTA, LocalDateTime.now()));
        } else {
            return Either.right(juegos);
        }

    }

    @Override
    public Either<ApiError, List<Juego>> getJuegosDeJugador(String id) {

        List<Juego> juegos;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DBQueries.SELECT_JUEGO_DE_JUGADOR)) {
            preparedStatement.setString(1, id);
            rs = preparedStatement.executeQuery();
            juegos = readFile(rs);

        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }

        if (juegos.isEmpty()) {
            return Either.left(new ApiError(Constant.ERROR_AL_CARGAR_LOS_JUEGOS, LocalDateTime.now()));
        } else {
            return Either.right(juegos);
        }
    }

    @Override
    public Either<ApiError, List<Juego>> getJuegosDeCompany(String companynombre) {
        List<Juego> juegos;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DBQueries.SELECT_JUEGOS_DE_COMPANY)) {
            preparedStatement.setString(1, companynombre);
            rs = preparedStatement.executeQuery();
            juegos = readFile(rs);

        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }

        if (juegos.isEmpty()) {
            return Either.left(new ApiError(Constant.ERROR_AL_CARGAR_LOS_JUEGOS, LocalDateTime.now()));
        } else {
            return Either.right(juegos);
        }
    }


    @Override
    public Either<ApiError, Boolean> deleteJuego(String id) {


        try (Connection con = db.getConnection();
             PreparedStatement preparedStatementJueg = con.prepareStatement(DBQueries.DELETE_JUEGO);
             PreparedStatement preparedStatementJuegComp = con.prepareStatement(DBQueries.DELETE_CONSOLAJUEGO_IDJUEGO)
        ) {
            preparedStatementJuegComp.setString(1, id);
            preparedStatementJueg.setString(1, id);

            preparedStatementJueg.executeUpdate();
            return Either.right(true);

        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }


    }

    @Override
    public Either<ApiError, Juego> addJuego(Juego j) {
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatementCuent = con.prepareStatement(DBQueries.INSERT_JUEGO);
        ) {
            preparedStatementCuent.setString(1, String.valueOf(getAllJuegos().get().size()));
            preparedStatementCuent.setString(2, j.getJugadorId());
            preparedStatementCuent.setString(3, j.getTituloJuego());
            preparedStatementCuent.setInt(4, j.getPegi());
            preparedStatementCuent.setDouble(5, j.getPrecio());
            preparedStatementCuent.setString(6, j.getDesarrolladora());

            preparedStatementCuent.executeUpdate();
            return Either.right(j);

        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }

    }

    @Override
    public Either<ApiError, Juego> update(Juego j) {

        try (Connection con = db.getConnection();
             PreparedStatement preparedStatementCuent = con.prepareStatement(DBQueries.UPDATE_JUEGO);
        ) {

            preparedStatementCuent.setString(1, j.getTituloJuego());
            preparedStatementCuent.setInt(2, j.getPegi());
            preparedStatementCuent.setDouble(3, j.getPrecio());
            preparedStatementCuent.setString(4, j.getDesarrolladora());
            preparedStatementCuent.setString(5, j.getJugadorId());
            preparedStatementCuent.setString(6, j.getJuegoId());

            preparedStatementCuent.executeUpdate();
            return Either.right(j);

        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }
    }

    @Override
    public Either<ApiError, Boolean> deleteJuegosDeCompany(String companyNombre) {
        List<Juego>juegos=new ArrayList<>();
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatementJueg = con.prepareStatement(DBQueries.SELECT_JUEGOS_DE_COMPANY);
             PreparedStatement preparedStatementJuegDel=con.prepareStatement(DBQueries.DELETE_JUEGO);
             PreparedStatement preparedStatementJuegComp = con.prepareStatement(DBQueries.DELETE_CONSOLAJUEGO_JUEGO)
        ) {
            preparedStatementJueg.setString(1, companyNombre);
            preparedStatementJuegComp.setString(1, companyNombre);
            rs=  preparedStatementJueg.executeQuery();
            juegos=readFile(rs);

            preparedStatementJuegComp.executeUpdate();
            juegos.forEach(juego -> {
                try {
                    preparedStatementJuegDel.setString(1,juego.getJuegoId());
                    preparedStatementJuegDel.executeUpdate();
                } catch (SQLException e) {
                    throw new BDDException(e.getMessage());
                }

            });
            return Either.right(true);

        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }

    }

    @Override
    public Either<ApiError, Integer> deleteListJuegos(List<String> ids) {
     int rows=0;
   for (String id:ids){
       try (Connection con = db.getConnection();
            PreparedStatement preparedStatementJueg = con.prepareStatement(DBQueries.DELETE_JUEGO);
            PreparedStatement preparedStatementJuegComp = con.prepareStatement(DBQueries.DELETE_CONSOLAJUEGO_IDJUEGO)
       ) {
           preparedStatementJuegComp.setString(1, id);
           preparedStatementJueg.setString(1, id);

          rows= preparedStatementJueg.executeUpdate();


       } catch (SQLException e) {
           throw new BDDException(e.getMessage());
       }
   }




     return Either.right(rows);

    }


    private List<Juego> readFile(ResultSet rs) {
        List<Juego> juegos = new ArrayList<>();
        try {


            while (rs.next()) {
                String juegoId = rs.getString(ConstantServer.JUEGO_ID);
                String jugadorId = rs.getString(ConstantServer.JUGADOR_ID);
                String tituloJuego = rs.getString(ConstantServer.TITULO_JUEGO);
                int pegi = rs.getInt(ConstantServer.PEGI);
                double precio = rs.getDouble(ConstantServer.PRECIO);

                String desarrolladora = rs.getString(ConstantServer.DESARROLLADORA);



                juegos.add(new Juego(juegoId, jugadorId, tituloJuego, pegi, precio, desarrolladora));
            }


        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }
        return juegos;
    }
}
