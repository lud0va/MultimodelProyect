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


}
