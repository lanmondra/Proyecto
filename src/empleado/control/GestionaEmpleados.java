package empleado.control;

import empleado.dominio.Empleado;
import java.util.Scanner;
import util.Color;
import empleado.dao.*;
import empleado.errores.codigoError;
import empleado.errores.userIncorrectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tienda.control.GestionTienda;

public class GestionaEmpleados {

    Scanner scan = new Scanner(System.in);

    ControladorEmpleado controlador;
    Empleado empleadoAutenticado;

    public GestionaEmpleados() {
        this.controlador = new ControladorEmpleado();
        this.empleadoAutenticado = null;
    }

    public void login() throws Exception {
        Scanner leerTeclado = new Scanner(System.in);
        boolean esEmpleadoValido = false;
        boolean esPasswordValido = false;

//        System.out.println("Bienvenido a la tienda");
//        System.out.println("***************************************");
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

        // empleadoAutenticado = controlador.getEmpleadoPorCodigo(codigoEntrada);
        empleadoAutenticado = controlador.getEmpleadoPorCodigo(codigoEntrada);
        if (empleadoAutenticado != null) {
            esEmpleadoValido = true;
            if (passwordEntrada.equals(empleadoAutenticado.getPassword())) {
                esPasswordValido = true;
            }
        }

        if (!esEmpleadoValido) {
            throw new Exception("empleado no valido" + codigoError.LOGIN_INCORRECTO);

            //throw new userIncorrectException(passwordEntrada, causa);
//            System.out.println("Usuario incorrecto");
        } else if (!esPasswordValido) {
            throw new Exception("Contraseña incorrecta" + codigoError.CONTRASEÑA_INCORRECTA);
            //System.out.println("Contraseña incorrecta");
        }

    }

    public Empleado getEmpleadoAutenticado() {
        return empleadoAutenticado;
    }

    public void actualizarPassword(Empleado empleado) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Password antigua: ");

        boolean next = true;
        while (next) {

            String password = scan.next();

            if (password.equals(empleado.getPassword())) {
                next = true;
                System.out.println(Color.GREEN + "entrada valida \n" + Color.DEFAULT);
                System.out.println("Entra la nueva contraseña: ");
                String newPassword = scan.next();
                var employees = new EmpleadoDAOImp();
                employees.updatePassword(empleado, newPassword);
                System.out.println(Color.GREEN + "Contraseña cambiada correctamente \n" + Color.DEFAULT);

                GestionTienda gestionTienda = new GestionTienda();

                gestionTienda.menu();
            } else {

            }
            System.err.println("la contraseña no coincide");
            System.out.println("pruebe de nuevo");
        }
    }

    public void cerrarSesion(Empleado empleado) {

        System.out.println("Entre el password para " + empleado.getNombre());
        boolean sigue = true;
        while (sigue) {

            String passw = scan.next();
            if (passw.equals(empleado.getPassword())) {

                System.out.println(Color.GREEN + "ha salido de la cuenta de " + empleado.getNombre() + "\n" + Color.DEFAULT);

                sigue = false;

            } else {
                System.err.println("contaseña no valida");
                System.out.println("pruebe otra vez");
            }
        }
        GestionTienda gestionTienda = new GestionTienda();
        gestionTienda.iniciar();

    }

}
