package factura;

import empleado.control.GestionaEmpleados;
import empleado.dominio.Empleado;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import producto.dominio.Producto;
import producto.vista.VistaProducto;

import tienda.control.GestionTienda;
import tienda.control.MenuPrincipal;
import tienda.vista.VistaPedidos;
import tienda.vista.VistaProductos;
import tienda.vista.VistaTienda;
import util.Color;

public class Pedido {

    Empleado empleado;

    String archivoPorductos = "src/File/archivoProducto.txt";
    String BULE = "\033[36m";
    Scanner scan = new Scanner(System.in);
    String green = "\033[32;1m";

    ArrayList<Producto> newProductList = new ArrayList<Producto>();
    ArrayList<Producto> ProductList = new ArrayList<Producto>();
    private static ArrayList<Integer> codListProd = new ArrayList<Integer>();
    private static List<Double> priceProdList = new ArrayList<Double>();
    private static ArrayList<String> descProdList = new ArrayList<String>();
    private static ArrayList<String> nomListProd = new ArrayList<String>();

    private static ArrayList<Producto> productosList = new ArrayList<Producto>();

    public Pedido(Empleado empleadoLogado) {
        this.empleado = empleadoLogado;
        String nom = empleadoLogado.getNombre();

    }

    public Pedido() {
    }

    public Producto leeProductos() {

        Producto Productos = new Producto();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        NumberFormat formatoNumero = NumberFormat.getInstance(Locale.FRANCE);// 
        // dependiendo de la localidad es punto o coma
        Number numero;
        String liniaconDatos;

        try ( var archivo = Files.newBufferedReader(Paths.get(archivoPorductos))) {

            while (archivo.readLine() != null) {
                // codigo
                archivo.readLine();//saltar un 
                liniaconDatos = archivo.readLine().trim();
                numero = formatoNumero.parse(liniaconDatos);
                int codigo = numero.intValue();

                archivo.readLine();// salto
                //coger el nombre
                liniaconDatos = archivo.readLine().trim();
                String nombre = liniaconDatos;

                archivo.readLine();
                // descripcion
                liniaconDatos = archivo.readLine().trim();
                String descripcion = liniaconDatos;

                archivo.readLine();//saltar un 
                //precio
                liniaconDatos = archivo.readLine().trim();
                numero = formatoNumero.parse(liniaconDatos);
                double precio = numero.doubleValue();

                Productos = new Producto(codigo, nombre, descripcion, precio);
                productosList.add(Productos);

            }

        } catch (ParseException e) {
            System.out.println("erro de formato" + archivoPorductos);

        } catch (IOException ex) {
            System.out.println("error de lectuara" + archivoPorductos);

        }

        return Productos;

    }

    public void Pedido() {

        switch (VistaPedidos.opcionDesdeMenufactura()) {
            case AÑADIR_A_LA_CESTA:
                System.out.println("cuantos productos desea comprar");
                leeProductos();
                aniadirProductos();
                break;
            case VER_CESTA:
                verCesta(ProductList);
                break;
            case IMPRIMIR_FACTURA:
                imprimirFactura(ProductList, empleado);
                break;
            case TERMINAR_PEDIDO:
                terminar();
                break;

        }

    }

   


    private  void aniadirProductos() {

        for (Producto p : productosList) {
            nomListProd.add(p.getNombre());
            codListProd.add(p.getCodigo());
            descProdList.add(p.getDescripcion());
            priceProdList.add(p.getPrecio());

        }

        System.out.println("elija la cantida de productos que seas comprar");
        int h = scan.nextInt();
        int aux = 0;
        while (aux < h) {
            int op = 0;
            System.out.println("elija el producto\n");

            int cont = 0;
            System.out.println("\t---------------------------------------");
            for (int i = 0; i < nomListProd.size(); i++) {
                cont++;
                System.out.println("\tproducto " + cont + " : " + nomListProd.get(i));

            }
            System.out.println("\t---------------------------------------");
            System.out.println("\nelija un producto del " + 1 + " al " + cont);

            op = scan.nextInt();
            switch (op) {
                case 1:

                    System.out.println("ha elegido :" + nomListProd.get(0));
                    System.out.println(green + "se ha guardado correctamente ");

                    for (int i = 0; i < productosList.size() - 2; i++) {

                        ProductList.add(productosList.get(i));

                    }
                    break;
                case 2:
                    System.out.println("ha elegido :" + nomListProd.get(0 + aux));
                    System.out.println(green + "se ha guardado correctamente ");
                    for (int i = 1; i < productosList.size() - 1; i++) {

                        ProductList.add(productosList.get(i));

                    }

                    break;
                case 3:
                    System.out.println("ha elegido :" + nomListProd.get(0 + aux));
                    System.out.println(green + "se ha guardado correctamente ");
                    for (int i = 2; i < productosList.size(); i++) {

                        ProductList.add(productosList.get(i));

                    }

                    break;
                default:
                    System.err.println("el dato introducido no es correcto");
                    break;

            }
            aux++;
            System.out.println("");

        }

        System.out.println("esto se guardada en el nuevo archivo");
//        abrirArchivo(myFile);
        for (int i = 0; i < ProductList.size(); i++) {
            System.out.println(ProductList.get(i));
        }

        Pedido();

    }

    private void verCesta(List ProductList) {

        if (ProductList.size() > 0) {
            System.out.println("precio de la cesta :\n");
            double aux = 0;
            for (int i = 0; i < ProductList.size(); i++) {

                aux = aux + this.ProductList.get(i).getPrecio();

            }
            System.out.println(aux + " €");

        } else {
            System.out.println("LA CESTA ESTA VACIA");
        }
        Pedido();
    }

    private  void imprimirFactura(List ProductList, Empleado empleado) {

        if (ProductList.size() > 0) {
            double aux = 0;
            System.out.println("\nFactura simplificada:");

            System.out.println("----------------------------------------");
            for (int i = 0; i < ProductList.size(); i++) {
                System.out.printf("Codigo:\t\t%d%nNombre:\t\t%s%nDescripción:\t%s%nPrecio\t\t%.2f%n%n", this.ProductList.get(i).getCodigo(),
                        this.ProductList.get(i).getNombre(), this.ProductList.get(i).getDescripcion(), this.ProductList.get(i).getPrecio());

            }

            System.out.println("----------------------------------------");
            for (int i = 0; i < ProductList.size(); i++) {

                aux = aux + this.ProductList.get(i).getPrecio();

            }

            System.out.println("El precio TOTAL es: " + aux + "€");

            System.out.println("Atendido por: " + empleado.getNombre() + " " + empleado.getApellidos());
            Pedido();

        } else {
            System.out.println("NO SE PUEDE IMPRIMIR POR QUE NO HAY PRODUCTOS");
        }

    }

    private void terminar() {
        GestionTienda gestionTienda = new GestionTienda();
        gestionTienda.menu();

    }
}
