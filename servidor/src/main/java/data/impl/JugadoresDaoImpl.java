package data.impl;

import data.JugadoresDao;
import data.impl.commondatabase.DBConnection;
import data.impl.commondatabase.DBQueries;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Jugador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JugadoresDaoImpl implements JugadoresDao {
    private final DBConnection db;
    private ResultSet rs;

    @Inject
    public JugadoresDaoImpl(DBConnection db) {
        this.db = db;
    }

    @Override
    public Either<ApiError, Jugador> addJugador(Jugador jugador) {
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatementCuent = con.prepareStatement(DBQueries.INSERT_JUGADOR);
        ) {
            preparedStatementCuent.setString(1, jugador.getId());
            preparedStatementCuent.setString(2, jugador.getNombre());
            preparedStatementCuent.setString(3, jugador.getApellido());
            preparedStatementCuent.setInt(4, jugador.getEdad());

            preparedStatementCuent.executeUpdate();
            return Either.right(jugador);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
