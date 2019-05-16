package tienda.vista;

import empleado.dominio.Empleado;
import java.util.List;
import java.util.Scanner;
import producto.control.GestionaProductos;
import producto.dao.ProductoDAO;
import producto.dominio.Producto;
import tienda.control.GestionTienda;
import tienda.control.MenuPrincipal;
import tienda.control.MenuProductos;
import util.Color;

public class VistaProductos {

    public static MenuProductos opcionDesdeMenuProductos() {
        borrarPantalla();
        System.out.println("--------Menú productos ------------");
        System.out.println("1.1 ._Modificar nombre de Producto");
        System.out.println("1.2 ._Modificar precio de Producto");
        System.out.println("1.3 ._Modificar codigo de Producto");
        System.out.println("1.4 ._Terminar ");
        System.out.println("------------------------------------");

        int opcion = pedirOpcionEnRango(1, 4);

        MenuProductos menu = null;

        switch (opcion) {
            case 1:
                menu = MenuProductos.MODIFICAR_NOMBRE;
                GestionaProductos g = new GestionaProductos();
                g.updateName();

                break;
            case 2:
                menu = MenuProductos.MODIFICAR_PRECIO;

                GestionaProductos gestiona = new GestionaProductos();
                gestiona.updatePrice();
                break;
            case 3:
                menu = MenuProductos.MODIFICAR_CODIGO;
                GestionaProductos gestion = new GestionaProductos();
                gestion.updateCode();
                break;
            case 4:
                menu = MenuProductos.SALIR;
                GestionTienda gestionTienda = new GestionTienda();

                gestionTienda.menu();

                break;
        }

        return menu;

    }

    private static int pedirOpcionEnRango(int min, int max) {

        Scanner leerTeclado = new Scanner(System.in);
        int opcion = 0;
        boolean hayError = true;

        while (hayError) {
            System.out.print("Seleccione una opción: ");
            if (leerTeclado.hasNextInt()) {
                opcion = leerTeclado.nextInt();
                hayError = opcion < min || opcion > max;
                if (hayError) {
                    muestraMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]", Color.ERROR);
                }
            } else {
                muestraMensaje("Error, opción no válida. Debe ser entre [" + min + "," + max + "]", Color.ERROR);
                leerTeclado.next();
            }
        }

        return opcion;
    }

    public static void mensajeBienvenida(Empleado empleado) {
        muestraMensaje("Bienvenido " + empleado.getNombre());
        System.out.println();
    }

    public static void muestraMensaje(String mensaje, Color color) {
        System.out.println(color + mensaje + Color.DEFAULT);
    }

    public static void muestraMensaje(String mensaje) {
        muestraMensaje(mensaje, Color.DEFAULT);
    }

    private static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}
