package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.List;

import com.techlab.productos.Producto;
import com.techlab.productos.ProductoService;

public class PedidoService {
    private static final List<Pedido> listaPedidos = new ArrayList<>();

    public static void crearPedido() {
        Pedido nuevoPedido = new Pedido();

        while (true) {
            System.out.println("Estos son los productos con los que contamos actualmente:");
            ProductoService.listar();

            int id = PedidoUtils.leerEntero("Ingrese el ID del producto que quiera pedir (0 para salir): ");
            if (id == 0) {
                if (nuevoPedido.estaVacio()) {
                    System.out.println("Se canceló el pedido.");
                    return;
                }
                break;
            }

            if (!idValido(id)) continue;

            Producto producto = Producto.getListaProducto().get(id - 1);

            if (producto.getCantStock() == 0) {
                System.out.println("Actualmente no contamos con stock para ese producto.");
                continue;
            }

            int cantidad = PedidoUtils.leerEntero("Ingrese la cantidad: ");
            boolean agregado = agregarProductoAlPedido(nuevoPedido, producto, cantidad);

            if (agregado && !PedidoUtils.confirmar("¿Quiere agregar otro producto? (s/n): ")) {
                break;
            }
        }

        if (!nuevoPedido.estaVacio()) {
            listaPedidos.add(nuevoPedido);
            System.out.println("Pedido creado con éxito.");
        } else {
            System.out.println("No se agregó ningún producto. Pedido cancelado.");
        }
    }

    private static boolean idValido(int id) {
        if (id <= 0 || id > Producto.getListaProducto().size()) {
            System.out.println("Ingrese un ID listado.");
            return false;
        }
        return true;
    }

    private static boolean agregarProductoAlPedido(Pedido pedido, Producto producto, int cantidad) {
        if (cantidad > producto.getCantStock()) {
            System.out.println("Stock insuficiente para " + producto.getNombre());
            return false;
        }

        DetallePedido detalleExistente = buscarDetalleExistente(pedido, producto);

        if (detalleExistente != null) {
            detalleExistente.agregarCantPedida(cantidad);
        } else {
            pedido.agregarProducto(new DetallePedido(cantidad, producto.getNombre(), producto.getPrecio(), producto));
        }

        producto.setCantStock(producto.getCantStock() - cantidad);
        return true;
    }

    private static DetallePedido buscarDetalleExistente(Pedido pedido, Producto producto) {
        for (DetallePedido detalle : pedido.getProductosPedidos()) {
            if (detalle.getProducto() == producto) {
                return detalle;
            }
        }
        return null;
    }

    public static void listarPedidos() {
        if (listaPedidos.isEmpty()) {
            System.out.println("Todavía no se han creado pedidos.");
            return;
        }

        System.out.println("""
        ==============================
        |      LISTA DE PEDIDOS      |
        ==============================
        """);

        int numPedido = 1;
        for (Pedido pedido : listaPedidos) {
            mostrarPedido(pedido, numPedido++);
        }
    }

    private static void mostrarPedido(Pedido pedido, int numero) {
        System.out.println("Pedido #" + numero);
        for (DetallePedido item : pedido.getProductosPedidos()) {
            System.out.println(" - " + item.getNombre() + " | $" + item.getPrecio() +
                    " * " + item.getCantPedida() + " = $" + item.getPrecioTotal());
        }
        System.out.println("TOTAL DEL PEDIDO: $" + pedido.getTotal());
        System.out.println("=========================================\n");
    }
}
