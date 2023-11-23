package dao.impl;

import dao.CuentaDao;
import dao.impl.commondatabase.DBConnection;
import dao.impl.commondatabase.DBQueries;
import errores.ApiError;
import io.vavr.control.Either;
import model.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CuentaDaoImpl implements CuentaDao {
    private final DBConnection db;
    private ResultSet rs;

    public CuentaDaoImpl(DBConnection db) {
        this.db = db;
    }


    @Override
    public Either<ApiError, Cuenta> getCuenta(int idcuenta) {

       Cuenta cuenta;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DBQueries.SELECT_JUEGO_DE_JUGADOR)) {
            preparedStatement.setInt(1, idcuenta);
            rs = preparedStatement.executeQuery(DBQueries.SELECT_JUEGO);
            cuenta = readFile(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (cuenta == null) {
            return Either.left(new ApiError("Error al cargar la cuenta", LocalDateTime.now()));
        } else {
            return Either.right(cuenta);
        }
    }

    @Override
    public Cuenta addCuenta(Cuenta cuenta) {
        return null;
    }

    private  Cuenta readFile(ResultSet rs) {
        Cuenta cuenta=new Cuenta();
        try {


            while (rs.next()) {
                String id = rs.getString("id");
                String  nombreUsuario = rs.getString("nombreUsuario");
                String password = rs.getString("password");
                String correoElectronico = rs.getString("correoElectronico");
                cuenta=new Cuenta(id, nombreUsuario, password,correoElectronico);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cuenta;
    }
}
