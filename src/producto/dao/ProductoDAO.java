package producto.dao;

import java.util.List;
import producto.dominio.Producto;

public interface ProductoDAO {

    List<Producto> leerProductos();  //Read

    boolean actualizarProducto(Producto producto); // Update
}
