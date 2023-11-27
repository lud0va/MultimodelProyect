package data.impl;


import config.ConstantServer;
import data.CuentaDao;
import data.impl.commondatabase.DBConnection;
import data.impl.commondatabase.DBQueries;
import errores.ApiError;
import errores.exceptions.BDDException;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import model.Cuenta;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


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
            throw new BDDException(e.getMessage());
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
            throw new BDDException(e.getMessage());
        }

        if (cuenta.isEmpty()) {
            return Either.left(new ApiError(ConstantServer.ERROR_AL_CARGAR_LA_CUENTA, LocalDateTime.now()));
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
            throw new BDDException(e.getMessage());
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
            throw new BDDException(e.getMessage());
        }
        if (cuenta.get(0).getId()==null){
            return Either.right(false);
        }else {
            return Either.right(true);
        }

    }

    private List<Cuenta> readFile(ResultSet rs) {
        List<Cuenta> cuenta=new ArrayList<>() ;
        try {


            while (rs.next()) {
                String id = rs.getString(ConstantServer.ID);
                String nombreUsuario = rs.getString(ConstantServer.NOMBRE_USUARIO);
                String password = rs.getString(ConstantServer.PASSWORD);
                String correoElectronico = rs.getString(ConstantServer.CORREO_ELECTRONICO);



                cuenta.add(new Cuenta(id, nombreUsuario, password, correoElectronico));
            }


        } catch (SQLException e) {
            throw new BDDException(e.getMessage());
        }
        return cuenta;
    }
}
