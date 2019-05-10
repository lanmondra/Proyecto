package persistencia.def;

import dominio.Producto;
import java.util.List;

public interface ProductoDAO {

    List<Producto> leerProductos();  //Read

    boolean actualizarProductos(List<Producto> productos); // Update
}
