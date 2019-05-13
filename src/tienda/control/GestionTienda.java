package tienda.control;

import empleado.control.GestionaEmpleados;
import empleado.dominio.Empleado;
import java.util.ArrayList;
import java.util.List;
import producto.dominio.Producto;
import tienda.vista.VistaTienda;

public class GestionTienda {

    private Empleado empleadoAutenticado;
    private List<Producto> cesta;
    GestionaEmpleados gestionaEmpleados;

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
        VistaTienda.mensajeBienvenida(empleadoAutenticado);

        switch (VistaTienda.opcionDesdeMenuPrincipal()) {
            case HACER_PEDIDO:
                //hacerPedido
                break;
            case MODIFICAR_PRODUCTO:
                // modificarProducto
                break;
            case CAMBIAR_PASSWORD:
                // cambiarPassword
                break;
            case CERRAR_SESION:
                // cerrarSesion
                break;
        }

    }

}
