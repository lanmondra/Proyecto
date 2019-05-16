package tienda.control;

import empleado.control.GestionaEmpleados;
import empleado.dominio.Empleado;
import factura.Pedido;
import java.util.ArrayList;
import java.util.List;
import producto.control.ControladorProducto;
import producto.control.GestionaProductos;
import producto.dominio.Producto;
import producto.vista.VistaProducto;
import tienda.vista.VistaProductos;
import tienda.vista.VistaTienda;

public class GestionTienda {

    private Empleado empleadoAutenticado;
    private List<Producto> cesta;
    private GestionaEmpleados gestionaEmpleados;
    private GestionaEmpleados gestionaProductos;

    public GestionTienda() {
        empleadoAutenticado = null;
        cesta = new ArrayList<>();
        gestionaEmpleados = new GestionaEmpleados();
    }

    public void iniciar() {
        boolean esLoginCorrecto = false;
        while (!esLoginCorrecto) {
            try {
                gestionaEmpleados.login();
                esLoginCorrecto = true;
            } catch (Exception e) {
            }
        }

        empleadoAutenticado = gestionaEmpleados.getEmpleadoAutenticado();
        // VistaTienda.mensajeBienvenida(empleadoAutenticado);
        System.out.println("bienvenido " + empleadoAutenticado.getNombre());

        menu();

    }

    public void menu() {
        switch (VistaTienda.opcionDesdeMenuPrincipal()) {
            case HACER_PEDIDO:
                Pedido pedido = new Pedido(empleadoAutenticado);
                pedido.Pedido();
                //hacerPedido
                break;
            case MODIFICAR_PRODUCTO:
                VistaProductos.opcionDesdeMenuProductos();
//                GestionaProductos c = new GestionaProductos();
//                c.menuProductos();

                break;
            case CAMBIAR_PASSWORD:
                GestionaEmpleados gestiona = new GestionaEmpleados();
                gestiona.actualizarPassword(empleadoAutenticado);
                // cambiarPassword
                break;
            case CERRAR_SESION:
                GestionaEmpleados g = new GestionaEmpleados();
                g.cerrarSesion(empleadoAutenticado);
                // cerrarSesion
                break;
        }
    }

}
