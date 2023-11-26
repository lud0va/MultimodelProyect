package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

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


}
