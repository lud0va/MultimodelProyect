package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Jugador {
    private UUID id ;
    private UUID cuentaId ;
    private String nombre ;
    private String apellido;
    private int edad;
}
