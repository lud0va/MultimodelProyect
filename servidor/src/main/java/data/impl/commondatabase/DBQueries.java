package data.impl.commondatabase;

public class DBQueries {

    public static final String SELECT_JUEGO="select * from Juego";
    public static final String SELECT_JUEGO_DE_JUGADOR="select * from Juego where jugadorId= ?";

    public static final String SELECT_CUENTA_POR_ID="select * from Cuenta where id=?";

    public static final String SELECT_CUENTA_TO_LOGIN="select * from Cuenta where nombreUsuario=? and password=?";

    public static final String INSERT_CUENTA="INSERT INTO Cuenta (id,nombreUsuario,password,correoElectronico) VALUES(?,?,?,?)";

    public static final String DELETE_JUEGO="delete from Juego where juegoId=?";
    public static final String ADD_JUEGO="INSERT INTO Juego (juegoId,jugadorId,tituloJuego,pegi,precio,desarrolladora)";
    public static final String UPDATE_JUEGO="update Juego set tituloJuego=?, pegi=?,precio=?,desarrolladora=?";


}
