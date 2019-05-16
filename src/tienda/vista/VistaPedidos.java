package tienda.vista;

import java.util.Scanner;
import tienda.control.MenuFactura;
import tienda.control.MenuProductos;
import util.Color;
import static util.Color.BULE;
import factura.*;

public class VistaPedidos {

    public static MenuFactura opcionDesdeMenufactura() {

        System.out.println("------------------------------------------");
        System.out.println(BULE + "------------MENU PEDIDO------------------" + Color.DEFAULT);
        System.out.println("1.1 ._Añadir un producto a la sesta");
        System.out.println("1.2 ._Visualizar el precio total de la sesta");
        System.out.println("1.3 ._Imprimir factura");
        System.out.println("1.4 ._Terminar pedido");
        System.out.println("------------------------------------------");

        int opcion = pedirOpcionEnRango(1, 4);

        MenuFactura menu = null;

        switch (opcion) {
            case 1:
                menu = MenuFactura.AÑADIR_A_LA_CESTA;

                break;
            case 2:
                menu = MenuFactura.VER_CESTA;

                break;
            case 3:
                menu = MenuFactura.IMPRIMIR_FACTURA;

                break;
            case 4:
                menu = MenuFactura.TERMINAR_PEDIDO;

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

//    public static void mensajeBienvenida(Empleado empleado) {
//        muestraMensaje("Bienvenido " + empleado.getNombre());
//        System.out.println();
    //  }
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
