package empleado.control;

import empleado.dominio.Empleado;
import java.util.Scanner;
import util.Color;

public class GestionaEmpleados {

    ControladorEmpleado controlador;
    Empleado empleadoAutenticado;

    public GestionaEmpleados() {
        this.controlador = new ControladorEmpleado();
        this.empleadoAutenticado = null;
    }

    public void login() {
        Scanner leerTeclado = new Scanner(System.in);
        boolean esEmpleadoValido = false;
        boolean esPasswordValido = false;

        System.out.println("Bienvenido a la tienda");
        System.out.println("***************************************");

        System.out.print("Introduce el código de tu usuario: ");
        while (!leerTeclado.hasNextInt()) {
            System.out.println(Color.ERROR + "Debe escribir un valor numérico" + Color.DEFAULT);
            System.out.print("Introduce el código de tu usuario: ");
            leerTeclado.next();
        }
        int codigoEntrada = leerTeclado.nextInt();

        System.out.print("Introduce tu contraseña: ");
        String passwordEntrada = leerTeclado.next();

        System.out.println("***************************************");
        System.out.println("");

        empleadoAutenticado = controlador.getEmpleadoPorCodigo(codigoEntrada);
        if (empleadoAutenticado != null) {
            esEmpleadoValido = true;
            if (passwordEntrada.equals(empleadoAutenticado.getPassword())) {
                esPasswordValido = true;
            }
        }

        if (!esEmpleadoValido) {
            //throw new ;
            System.out.println("Usuario incorrecto");
        } else if (!esPasswordValido) {
            //throw new ;
            System.out.println("Contraseña incorrecta");
        }


    }

    public Empleado getEmpleadoAutenticado() {
        return empleadoAutenticado;
    }
    
    

}
