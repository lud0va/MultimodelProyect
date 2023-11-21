package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {
    private String jugadorId;
    private String nombre;
    private String apellido;
    private int edad;
}
