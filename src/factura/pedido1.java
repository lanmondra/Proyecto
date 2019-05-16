/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factura;

import empleado.dominio.Empleado;
import java.util.List;
import producto.dominio.Producto;

/**
 *
 * @author miguelmondragon
 */
public class pedido1 {

    private Empleado empleado;
    private List<Producto> productos;
    private double totalPrecio;

    public pedido1(Empleado empleado, List<Producto> productos, double totalPrecio) {
        this.empleado = empleado;
        this.productos = productos;
        this.totalPrecio = totalPrecio;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

}
