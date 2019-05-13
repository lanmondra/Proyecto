package empleado.control;

import empleado.dao.EmpleadoDAO;
import empleado.dao.EmpleadoDAOImp;
import empleado.dominio.Empleado;
import java.util.List;

public class ControladorEmpleado {

    public List<Empleado> leerEmpleados() {
        EmpleadoDAO edao = new EmpleadoDAOImp();
        return edao.leerEmpleados();
    }

    public Empleado getEmpleadoPorCodigo(int codigo) {
        EmpleadoDAO edao = new EmpleadoDAOImp();
        return edao.getEmpleadoPorCodigo(codigo);
    }

    public boolean actualizarEmpleado(Empleado empleado) {
        EmpleadoDAO edao = new EmpleadoDAOImp();
        return edao.actualizarEmpleado(empleado);
    }
}
