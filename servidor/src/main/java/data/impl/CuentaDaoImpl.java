package data.impl;

import data.CuentaDao;
import data.impl.commondatabase.DBConnection;
import data.impl.commondatabase.DBQueries;
import errores.ApiError;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Cuenta;
import model.Juego;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CuentaDaoImpl implements CuentaDao {
    private final DBConnection db;
    private ResultSet rs;

    @Inject
    public CuentaDaoImpl(DBConnection db) {
        this.db = db;
    }

    public List<Cuenta> getAll(){
        List<Cuenta> cuentas;
        try (Connection con = db.getConnection();
             Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {

            rs = statement.executeQuery(DBQueries.SELECT_JUEGO);

            cuentas = readFile(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

       return cuentas;
    }


    @Override
    public Either<ApiError, Cuenta> getCuenta(String idcuenta) {

      List  <Cuenta> cuenta;
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
            return Either.right(cuenta.get(0));
        }
    }

    @Override
    public Either<ApiError,Cuenta> addCuenta(Cuenta cuenta) {

        try (Connection con = db.getConnection();
             PreparedStatement preparedStatementCuent = con.prepareStatement(DBQueries.INSERT_CUENTA);
        ) {
            preparedStatementCuent.setString(1, String.valueOf(getAll().size()+1));
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
        List<Cuenta> cuenta;
        try (Connection con = db.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DBQueries.SELECT_CUENTA_TO_LOGIN)) {
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2,passwrd);
                    rs = preparedStatement.executeQuery();
            cuenta = readFile(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (cuenta.get(0).getId()==null){
            return Either.right(false);
        }
        return Either.right(true);
    }

    private List<Cuenta> readFile(ResultSet rs) {
        List<Cuenta> cuenta=new ArrayList<>() ;
        try {


            while (rs.next()) {
                String id = rs.getString("id");
                String nombreUsuario = rs.getString("nombreUsuario");
                String password = rs.getString("password");
                String correoElectronico = rs.getString("correoElectronico");
              //  long numericValue = Long.parseLong(id);

                // Crear un UUID utilizando el valor numérico
              //  UUID idCuenta = new UUID(0, numericValue);
                cuenta.add(new Cuenta(id, nombreUsuario, password, correoElectronico));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cuenta;
    }
}
