package producto.dao;

import empleado.dominio.Empleado;
import java.util.List;
import producto.dominio.Producto;

public interface ProductoDAO {

    List<Producto> leerProductos();  //Read

    boolean actualizarProducto(Producto producto); // Update
    
    
    void addProduct(Producto newProduct);
    double readTotalPrice();
    void printInvoice(Empleado attendant);
}
