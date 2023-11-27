package cliente.ui.pantallas.add;

import lombok.Data;
import model.Juego;

import java.util.List;

@Data
public class AddState {
    private final List<Juego> juegos;
    private final String error;





}
