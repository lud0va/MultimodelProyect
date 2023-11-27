package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    private String id ;
    private String nombreUsuario ;
    private String password ;
    private String correoElectronico ;


    public Cuenta(String nombreUsuario, String password, String correoElectronico) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.correoElectronico = correoElectronico;
    }
}
