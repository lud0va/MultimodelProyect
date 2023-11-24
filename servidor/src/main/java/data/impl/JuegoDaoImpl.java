package data.impl;

import data.JuegoDao;
import data.impl.commondatabase.DBConnection;
import data.impl.commondatabase.DBQueries;
import errores.ApiError;
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
            throw new RuntimeException(e);
        }

        if (juegos.isEmpty()) {
            return Either.left(new ApiError("Error al cargar los juegos", LocalDateTime.now()));
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
            throw new RuntimeException(e);
        }

        if (juegos.isEmpty()) {
            return Either.left(new ApiError("Error al cargar los juegos", LocalDateTime.now()));
        } else {
            return Either.right(juegos);
        }
    }


    private List<Juego> readFile(ResultSet rs) {
        List<Juego> juegos = new ArrayList<>();
        try {


            while (rs.next()) {
                String juegoId = rs.getString("juegoId");
                String jugadorId = rs.getString("jugadorId");
                String tituloJuego = rs.getString("tituloJuego");
                int pegi = rs.getInt("pegi");
                double precio = rs.getDouble("precio");

                String desarrolladora = rs.getString("desarrolladora");
                UUID jueg=UUID.fromString(juegoId);
                UUID jugad=UUID.fromString(jugadorId);

                juegos.add(new Juego(jueg, jugad, tituloJuego, pegi, precio, desarrolladora));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return juegos;
    }
}
