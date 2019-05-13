package empleado.dominio;

public class Empleado {

    private int codigo;
    private String nombre;
    private String apellidos;
    private String password;

    public Empleado(int codigo, String nombre, String apellidos, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.password = password;
    }

    public Empleado() {
        this(0, null, null, null);
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

    @Override
    public String toString() {
        return String.format("%d %s %s %s%n",
                getCodigo(), getNombre(), getApellidos(), getPassword());
    }

}
