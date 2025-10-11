package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<DetallePedido> productosPedidos = new ArrayList<>();

    public void agregarProducto(DetallePedido detallePedido) {
        productosPedidos.add(detallePedido);
    }

    public boolean estaVacio() {
        return productosPedidos.isEmpty();
    }

    public List<DetallePedido> getProductosPedidos() {
        return productosPedidos;
    }

    public double getTotal() {
        return productosPedidos.stream()
                .mapToDouble(DetallePedido::getPrecioTotal)
                .sum();
    }
}