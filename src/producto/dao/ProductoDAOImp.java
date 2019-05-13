package producto.dao;

import conexion.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import producto.dominio.Producto;

public class ProductoDAOImp implements ProductoDAO {

    @Override
    public List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM productos";

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement();
                var resultado = sentencia.executeQuery(query);) {

            // capturar los resultados
            while (resultado.next()) {
                var codigo = resultado.getInt("p_codigo");
                var nombre = resultado.getString("p_nombre");
                var descripcion = resultado.getString("p_descripcion");
                var precio = resultado.getDouble("p_precio");

                productos.add(new Producto(codigo, nombre, descripcion, precio));
            }

        } catch (SQLException e) {
            System.out.println("Error al leer los productos en la base de datos "
                    + this.getClass().getName());
        }

        return productos;
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
