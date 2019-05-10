package dominio;

import java.util.List;
import persistencia.imp.EmpleadoDAOImp;

public class Empleado {
    private int codigo;
    private String nombre;
    private String apellidos;
    private String password;
    private static EmpleadoDAOImp empleadoDAO;

    public Empleado(int codigo, String nombre, String apellidos, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
    }

    public Empleado() {
        this(0, null, null, null);
    }
    
    static{
        empleadoDAO = new EmpleadoDAOImp();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static List<Empleado> leerEmpleados(){
        return empleadoDAO.leerEmpleados();
    }
    
    public static Empleado getEmpleadoPorCodigo(int codigo){
        return empleadoDAO.getEmpleadoPorCodigo(codigo);
    }
    
    
    public static boolean actualizarEmpleados(Empleado empleado){
        return empleadoDAO.actualizarEmpleado(empleado);
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s%n",
                getCodigo(),getNombre(),getApellidos(),getPassword());
    }
    
    
    
}
