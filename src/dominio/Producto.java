package dominio;

import java.util.List;
import persistencia.imp.ProductoDAOImp;

public class Producto {

    private int codigo;
    private String nombre;
    private String descripcion;
    private double precio;
    static private ProductoDAOImp productoDAO;

    public Producto(int codigo, String nombre, String descripcion, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public Producto() {
        this(0, null, null, 0);
    }

    static {
        productoDAO = new ProductoDAOImp();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    static public List<Producto> leerProductos() {
        return productoDAO.leerProductos();
    }

    static public boolean actualizarProductos(List<Producto> productos) {
        return productoDAO.actualizarProductos(productos);
    }

    @Override
    public String toString() {
        return String.format("%d %s %.2f%n",
                getCodigo(), getNombre(), getPrecio());

    }

}
