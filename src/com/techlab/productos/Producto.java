package com.techlab.productos;

import java.util.ArrayList;
import java.util.List;

public class Producto {
	private String nombre;
	private double precio;
	private int cantStock;
	private static List<Producto> listaProducto = new ArrayList<>();
	
	public Producto(String nombre, double precio, int cantStock) {
		this.nombre = nombre;
		this.precio = precio;
		this.cantStock = cantStock;
	}
	
	public static void agregar(Producto producto) {
		listaProducto.add(producto);
	}
	
	public String toString() {
        String stockActual;

        if (this.cantStock == 0) {
        	stockActual = "Stock no disponible";
        } else {
        	stockActual = "Stock: " + this.cantStock;
        }

        return "Producto: " + nombre + " | Precio: $" + precio + " | " + stockActual;

    }
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getCantStock() {
		return cantStock;
	}

	public void setCantStock(int cantStock) {
		this.cantStock = cantStock;
	}

	public static List<Producto> getListaProducto() {
		return listaProducto;
	}
	
	
}
