package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Juego {
    private String juegoId  ;
    private String jugadorId ;
    private String tituloJuego ;
    private int pegi;

    private double precio;
    private String desarrolladora;

    public Juego(String tituloJuego, int pegi, double precio, String desarrolladora) {
        this.tituloJuego = tituloJuego;
        this.pegi = pegi;
        this.precio = precio;
        this.desarrolladora = desarrolladora;
    }
}
