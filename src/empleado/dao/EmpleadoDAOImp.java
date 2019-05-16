package empleado.dao;

import conexion.ConexionBD;
import empleado.dominio.Empleado;
import java.sql.SQLException;
import java.util.List;
import empleado.dao.EmpleadoDAO;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class EmpleadoDAOImp implements EmpleadoDAO {
    private List<Empleado> empleados;
    
    public EmpleadoDAOImp() {
        String archivoEmple = "src/File/archivoEmple.txt";
        Path path = Paths.get(archivoEmple);

        List<Empleado> employeesList = new ArrayList<Empleado>();
        
        try (var reader = Files.newBufferedReader(path)) {

            int employeeAccessCode = 0;
            String employeeName = null;
            String employeeLastName = null;
            String employeePassword = null;

            while (reader.readLine() != null) {
              
                reader.readLine();
                employeeAccessCode = Integer.parseInt(reader.readLine().trim());
                
                reader.readLine();
                employeeName = reader.readLine().trim();
                
                reader.readLine();
                employeeLastName = reader.readLine().trim();
                
                reader.readLine();
                employeePassword = reader.readLine().trim();
              
                employeesList.add(new Empleado(employeeAccessCode, employeeName, employeeLastName, employeePassword));
                
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }

       setEmpleados(employeesList);
        

    }
    
    public List<Empleado> getEmpleados() {
        return this.empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
    
    @Override
    public List<Empleado> leerEmpleados() {
        String archivoEmple = "src/File/archivoEmple.txt";
   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        List<Empleado> empleadoList = new ArrayList<>();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        NumberFormat formatoNumero = NumberFormat.getInstance(Locale.FRANCE);// 
        // dependiendo de la localidad es punto o coma
        Number numero;
        String liniaconDatos;

    
        try ( var archivo = Files.newBufferedReader(Paths.get(archivoEmple))) {

            while (archivo.readLine() != null) {
                // codigo
                archivo.readLine();//saltar un 
                liniaconDatos = archivo.readLine().trim();
                numero = formatoNumero.parse(liniaconDatos);
                int codigoComp = numero.intValue();

                archivo.readLine();// salto
                //coger el nombre
//                liniaconDatos = archivo.readLine().trim();
                String nombre = archivo.readLine().trim();

                archivo.readLine();
                // descripcion
//                liniaconDatos = archivo.readLine().trim();
                String apellidos = archivo.readLine().trim();

                archivo.readLine();//saltar un 
                //precio
//                liniaconDatos = archivo.readLine().trim();
//                numero = formatoNumero.parse(liniaconDatos);
                String password = archivo.readLine().trim();
                
                empleadoList.add(new Empleado(codigoComp, nombre, apellidos, password));
            }}
        catch (ParseException e) {
            System.out.println("erro de formato" + archivoEmple);

        } catch (IOException ex) {
            System.out.println("error de lectuara " + archivoEmple);

        }
        return empleadoList;
        }
    

             
    

//    @Override
    public Empleado getEmpleadoPorCodigos(int codigo) {
        Empleado empleado = null;
        String query = "SELECT * FROM empleados where e_codigo = " + codigo;

        try (
                 var conexion = ConexionBD.conectar();  var sentencia = conexion.createStatement();  var resultado = sentencia.executeQuery(query)) {

            resultado.next();
            var code = resultado.getInt("e_codigo");
            var nombre = resultado.getString("e_nombre");
            var apellidos = resultado.getString("e_apellidos");
            var password = resultado.getString("e_password");
            empleado = new Empleado(codigo, nombre, apellidos, password);

        } catch (SQLException e) {
            System.out.println("Error al cargar empleado con el codigo " + codigo);
        }

        return empleado;
    }

    @Override
    public boolean actualizarEmpleado(Empleado empleado) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    
    
    public Empleado getEmpleadoPorCodigo(int codigo) {
        String archivoEmple = "src/File/archivoEmple.txt";
   // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Empleado empleado = new Empleado();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        NumberFormat formatoNumero = NumberFormat.getInstance(Locale.FRANCE);// 
        // dependiendo de la localidad es punto o coma
        Number numero;
        String liniaconDatos;

        try ( var archivo = Files.newBufferedReader(Paths.get(archivoEmple))) {

            while (archivo.readLine() != null) {
                // codigo
                archivo.readLine();//saltar un 
                liniaconDatos = archivo.readLine().trim();
                numero = formatoNumero.parse(liniaconDatos);
                int codigoComp = numero.intValue();

                archivo.readLine();// salto
                //coger el nombre
//                liniaconDatos = archivo.readLine().trim();
                String nombre = archivo.readLine().trim();

                archivo.readLine();
                // descripcion
//                liniaconDatos = archivo.readLine().trim();
                String apellidos = archivo.readLine().trim();

                archivo.readLine();//saltar un 
                //precio
//                liniaconDatos = archivo.readLine().trim();
//                numero = formatoNumero.parse(liniaconDatos);
                String password = archivo.readLine().trim();
                
                if(codigoComp==codigo){
                empleado = new Empleado(codigo, nombre, apellidos, password);
                }else{
                    
                }
            }

        } catch (ParseException e) {
            System.out.println("erro de formato" + archivoEmple);

        } catch (IOException ex) {
            System.out.println("error de lectuara " + archivoEmple);

        }

        return empleado;

    }
     
    public void escribirEnArchivo() {
        String employeeListString = this.toString();
        String archivoEmple = "src/File/archivoEmple.txt";
        try {
            FileWriter writer = new FileWriter(archivoEmple);
            writer.write(employeeListString);
            writer.close();
            
        } catch (IOException ex) {
            Logger.getLogger(EmpleadoDAOImp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updatePassword(Empleado employee, String employeePassword) {
        for (int i = 0; i < getEmpleados().size(); i++) {
            if(getEmpleados().get(i).getCodigo()== employee.getCodigo()) {
                this.empleados.get(i).setPassword(employeePassword);
            }
        }
        this.escribirEnArchivo();
    }
    
    @Override
    public String toString(){

        String productListString = "";
        for ( Empleado i : this.empleados) {
            productListString += 
                    "[empleado]" + 
                    "\n [codigo]\n " + 
                     i.getCodigo()+
                    "\n [nombre]\n " + 
                    i.getNombre()+
                    "\n [apellidos]\n " + 
                    i.getApellidos()+
                    "\n [contraseña]\n " +
                    i.getPassword() +
                    "\n";
        }
        return productListString;

    }
}
