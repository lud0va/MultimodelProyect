package data.impl;

import data.CuentaDao;
import data.impl.commondatabase.DBConnection;
import data.impl.commondatabase.DBQueries;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Cuenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;

public class CuentaDaoImpl implements CuentaDao {
    private final DBConnection db;
    private ResultSet rs;

    @Inject

    public CuentaDaoImpl(DBConnection db) {
        this.db = db;
    }




    @Override
    public Either<ApiError, Cuenta> getCuenta(String idcuenta) {

        Cuenta cuenta;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DBQueries.SELECT_CUENTA_POR_ID)) {
            preparedStatement.setString(1, idcuenta);
            rs = preparedStatement.executeQuery();
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
    public Either<ApiError,Cuenta> addCuenta(Cuenta cuenta) {

        try (Connection con = db.getConnection();
             PreparedStatement preparedStatementCuent = con.prepareStatement(DBQueries.INSERT_CUENTA);
        ) {
            preparedStatementCuent.setString(1, cuenta.getId().toString());
            preparedStatementCuent.setString(2, cuenta.getNombreUsuario());
            preparedStatementCuent.setString(3, cuenta.getPassword());
            preparedStatementCuent.setString(4, cuenta.getCorreoElectronico());

            preparedStatementCuent.executeUpdate();
            return Either.right(cuenta);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Either<ApiError, Boolean> doLogin(String usuario, String passwrd) {
        Cuenta cuenta;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DBQueries.SELECT_CUENTA_TO_LOGIN)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2,passwrd);
                    rs = preparedStatement.executeQuery();
            cuenta = readFile(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (cuenta.getId()==null){
            return Either.right(false);
        }
        return Either.right(true);
    }

    private Cuenta readFile(ResultSet rs) {
        Cuenta cuenta = new Cuenta();
        try {


            while (rs.next()) {
                String id = rs.getString("id");
                String nombreUsuario = rs.getString("nombreUsuario");
                String password = rs.getString("password");
                String correoElectronico = rs.getString("correoElectronico");
                long numericValue = Long.parseLong(id);

                // Crear un UUID utilizando el valor numérico
                UUID idCuenta = new UUID(0, numericValue);
                cuenta = new Cuenta(idCuenta, nombreUsuario, password, correoElectronico);
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cuenta;
    }
}
