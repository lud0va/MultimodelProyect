package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    private String cuentaId;
    private String jugadorId;

    private String nombreUsuario;
    private String passwoed;
    private String correoElectronico;


}
