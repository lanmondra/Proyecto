package producto.control;

import java.util.List;
import producto.dao.ProductoDAO;
import producto.dao.ProductoDAOImp;
import producto.dominio.Producto;

public class ControladorProducto {

    static public List<Producto> leerProductos() {
        ProductoDAO pdao = new ProductoDAOImp();
        return pdao.leerProductos();
    }

    static public boolean actualizarProducto(Producto producto) {
        ProductoDAO pdao = new ProductoDAOImp();
        return pdao.actualizarProducto(producto);
    }

}
