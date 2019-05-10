package base;

import dominio.Empleado;
import dominio.Producto;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        cargarProductos();
        
        Empleado empleado = Empleado.getEmpleadoPorCodigo(111);
        System.out.println("Empleado:");
        System.out.println(empleado!=null?empleado:"No existe el empleado");
        
    }

    static void cargarProductos() {
        List<Producto> productos = Producto.leerProductos();

        System.out.println("\nProductos:");
        for (Producto producto : productos) {
            System.out.print(producto);
        }
        System.out.println("");
    }

}
