package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cuenta {
    private UUID id ;
    private String nombreUsuario ;
    private String password ;
    private String correoElectronico ;


}
