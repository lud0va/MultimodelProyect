package cliente.ui.pantallas.list;

import lombok.Data;
import model.Juego;

import java.util.List;
@Data
public class ListState {
    private final List<Juego> juegosBorrarMultiple;
    private final List<Juego>juegosBorrarFiltro;
    private final String error;
}
