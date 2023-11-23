package dao.impl.commondatabase;

public class DBQueries {

    public static final String SELECT_JUEGO="select * from Juego";
    public static final String SELECT_JUEGO_DE_JUGADOR="select * from juego where jugadorId=?";

    public static final String SELECT_CUENTA_POR_ID="select * from Cuenta where id=?";
}
