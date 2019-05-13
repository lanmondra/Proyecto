package empleado.dao;

import conexion.ConexionBD;
import empleado.dominio.Empleado;
import java.sql.SQLException;
import java.util.List;
import empleado.dao.EmpleadoDAO;

public class EmpleadoDAOImp implements EmpleadoDAO {

    @Override
    public List<Empleado> leerEmpleados() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Empleado getEmpleadoPorCodigo(int codigo) {
        Empleado empleado = null;
        String query = "SELECT * FROM empleados where e_codigo = " + codigo;

        try (
                var conexion = ConexionBD.conectar();
                var sentencia = conexion.createStatement();
                var resultado = sentencia.executeQuery(query)) {

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

}
