package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Juego {
    private String juegoId;
    private String tituloJuego;
    private String plataforma;
    private int pegi;

    private double precio;
    private String desarrolladora;

}
