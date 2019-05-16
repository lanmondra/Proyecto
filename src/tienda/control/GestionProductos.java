package tienda.control;

import empleado.dominio.Empleado;
import factura.Pedido;
import tienda.vista.VistaPedidos;

public class GestionProductos {

    Empleado empleado;

    public GestionProductos(Empleado empleadoLogado) {
        this.empleado = empleadoLogado;
        String nom = empleadoLogado.getNombre();

    }

}
