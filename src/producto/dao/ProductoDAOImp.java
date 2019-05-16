package producto.dao;

import conexion.ConexionBD;
import empleado.dominio.Empleado;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import producto.dominio.Producto;

public class ProductoDAOImp implements ProductoDAO {

    List<Producto> products;

    Scanner scan = new Scanner(System.in);

    String archivoPorduc = "src/File/archivoProducto.txt";

    // @Override
    public List<Producto> leerProductoas() {
        List<Producto> productos = new ArrayList<>();

        String query = "SELECT * FROM productos";

        try (
                 var conexion = ConexionBD.conectar();  var sentencia = conexion.createStatement();  var resultado = sentencia.executeQuery(query);) {

            // capturar los resultados
            while (resultado.next()) {
                var codigo = resultado.getInt("p_codigo");
                var nombre = resultado.getString("p_nombre");
                var descripcion = resultado.getString("p_descripcion");
                var precio = resultado.getDouble("p_precio");

                productos.add(new Producto(codigo, nombre, descripcion, precio));
            }

        } catch (SQLException e) {
            System.out.println("Error al leer los productos en la base de datos "
                    + this.getClass().getName());
        }

        return productos;
    }

    //@Override
    public ProductoDAOImp() {

        Path path = Paths.get(this.archivoPorduc);

        List<Producto> productList = new ArrayList<Producto>();

        try ( var reader = Files.newBufferedReader(path)) {

            int productCode = 0;
            String productName = null;
            String productDescription = null;
            double productPrice = 0.0;

            while (reader.readLine() != null) {

                reader.readLine();
                productCode = Integer.parseInt(reader.readLine().trim());

                reader.readLine();
                productName = reader.readLine().trim();

                reader.readLine();
                productDescription = reader.readLine().trim();

                reader.readLine();
                productPrice = Double.parseDouble(reader.readLine().trim().replace(',', '.'));

                productList.add(new Producto(productCode, productName, productDescription, productPrice));

            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        setProducts(productList);

    }

    // @Override
    public Producto leeProductos() {

        Producto Productos = new Producto();
        List<Producto> productoList = new ArrayList<>();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        NumberFormat formatoNumero = NumberFormat.getInstance(Locale.FRANCE);// 
        // dependiendo de la localidad es punto o coma
        Number numero;
        String liniaconDatos;

        try ( var archivo = Files.newBufferedReader(Paths.get(archivoPorduc))) {

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
                // productoList.add(Productos);
                productoList.add(new Producto(codigo, nombre, descripcion, precio));

            }

        } catch (ParseException e) {
            System.out.println("erro de formato" + archivoPorduc);

        } catch (IOException ex) {
            System.out.println("error de lectuara" + archivoPorduc);

        }

        return Productos;
    }

    // @Override
    public List<Producto> leerProductos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Producto> getProductos() {
        return products;
    }

    public void setProducts(List<Producto> products) {
        this.products = products;
    }

    public void updateCode(int productCode, int productNewCode) {
        Producto product = new Producto();

        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getCodigo() == productCode) {
                product.setDescripcion(this.products.get(i).getDescripcion());
                product.setPrecio(this.products.get(i).getPrecio());
                product.setNombre(this.products.get(i).getNombre());
                product.setCodigo(productNewCode);
                this.products.set(i, product);
            }
        }
        this.escribirEnArchivo();
    }

    public void updateName(int productCode, String nuwvoNombre) {

        Producto product = new Producto();
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getCodigo() == productCode) {
                product.setCodigo(this.products.get(i).getCodigo());
                product.setDescripcion(this.products.get(i).getDescripcion());
                product.setPrecio(this.products.get(i).getPrecio());
                product.setNombre(nuwvoNombre);
                this.products.set(i, product);
            }
        }
        this.escribirEnArchivo();
    }

    //@Override
    public void escribirEnArchivo() {
        String productListString = this.toString();
        String archivoPorduc = "src/File/archivoProducto.txt";
        try {
            FileWriter writer = new FileWriter(archivoPorduc);
            writer.write(productListString);
            writer.close();

        } catch (IOException ex) {
            Logger.getLogger(ProductoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updatePrice(int productCode, double productPrice) {
        Producto product = new Producto();
        for (int i = 0; i < this.products.size(); i++) {
            if (this.products.get(i).getCodigo() == productCode) {
                product.setDescripcion(this.products.get(i).getDescripcion());
                product.setPrecio(productPrice);
                product.setNombre(this.products.get(i).getNombre());
                product.setCodigo(this.products.get(i).getCodigo());
                this.products.set(i, product);
            }
        }
        this.escribirEnArchivo();
    }

    @Override
    public String toString() {

        String productListString = "";
        for (Producto i : this.products) {
            productListString
                    += "[producto]"
                    + "\n [codigo]\n "
                    + i.getCodigo()
                    + "\n [nombre]\n "
                    + i.getNombre()
                    + "\n [descripcion]\n "
                    + i.getDescripcion()
                    + "\n [precio]\n "
                    + i.getPrecio()
                    + "\n";
        }
        return productListString;
    }

    @Override
    public void addProduct(Producto newProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double readTotalPrice() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printInvoice(Empleado attendant) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizarProducto(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
