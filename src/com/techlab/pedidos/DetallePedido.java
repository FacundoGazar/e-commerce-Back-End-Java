package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class DetallePedido {
    private int cantPedida;
    private String nombre;
    private double precio;
    private Producto producto;

    public DetallePedido(int cantPedida, String nombre, double precio, Producto producto) {
        this.cantPedida = cantPedida;
        this.nombre = nombre;
        this.precio = precio;
        this.producto = producto;
    }

    public double getPrecioTotal() {
        return this.cantPedida * this.precio;
    }
    
    public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public int getCantPedida() {
        return cantPedida;
    }
	
    public Producto getProducto() {
		return producto;
	}

	public void agregarCantPedida(int cantExtra) {
        this.cantPedida += cantExtra;
    }
}
