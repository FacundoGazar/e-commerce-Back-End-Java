package com.techlab.productos;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.techlab.util.FormateoDeTexto;

public class ProductoService {
    private static final Scanner input = new Scanner(System.in);

    public static void agregar() {
        try {
            System.out.println("Por favor, ingrese los siguientes campos:");
            String nombre = leerTexto("Nombre: ").trim();

            String nombreFormateado = FormateoDeTexto.formatearNombreProducto(nombre);

            if (nombreFormateado.isEmpty()) {
                System.out.println("El nombre del producto no puede estar vacío.");
                return;
            }

            double precio = leerDouble("Precio: ");
            int stock = leerEntero("Stock: ");

            Producto producto = new Producto(nombreFormateado, precio, stock);
            Producto.agregar(producto);
            System.out.println("Producto agregado correctamente.");

        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un valor válido.");
            input.nextLine();
        }
    }

    public static void listar() {
        System.out.println("""
                ==================================
                |        Lista de productos      |
                ==================================
                """);

        List<Producto> productos = Producto.getListaProducto();

        if (productos.isEmpty()) {
            System.out.println("|        La Lista está vacía      |");
            System.out.println("==================================");
            return;
        }

        for (int i = 0; i < productos.size(); i++) {
            System.out.println("|ID:" + (i + 1) + " | " + productos.get(i) + "|");
            System.out.println("==================================");
        }
    }

    public static void buscarProducto() {
        try {
            System.out.println("""
                    Seleccione el método de búsqueda:
                    1. Buscar por Nombre.
                    2. Buscar por ID.
                    """);
            int opcion = leerEntero("Ingrese la opción: ");

            switch (opcion) {
                case 1 -> buscarPorNombre();
                case 2 -> buscarPorId();
                default -> System.out.println("Ingrese un número de los listados.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número.");
            input.nextLine();
        }
    }

    private static void buscarPorNombre() {
        String nombre = leerTexto("Ingrese el nombre que desea buscar: ");
        String nombreFormateado = FormateoDeTexto.formatearNombreProducto(nombre);

        List<Integer> coincidencias = obtenerCoincidenciasPorNombre(nombreFormateado);

        if (coincidencias.isEmpty()) {
            System.out.println("No se encontró ningún producto con ese nombre.");
            return;
        }

        if (coincidencias.size() > 1) {
            System.out.println("Se encontraron " + coincidencias.size() + " resultados.");
            buscarConjuntoPorId(coincidencias);
        } else {
            mostrarYConfirmarModificacion(coincidencias.get(0));
        }
    }

    private static void buscarConjuntoPorId(List<Integer> coincidencias) {
        try {
            int id = leerEntero("Ingrese el ID del producto que desea modificar: ") - 1;

            if (!coincidencias.contains(id)) {
                System.out.println("Ingrese un ID válido.");
                return;
            }

            Producto p = Producto.getListaProducto().get(id);
            System.out.println("Seleccionaste el producto con ID: " + id);
            if (confirmar("¿Querés modificar el producto? (s/n): ")) {
                modificarProducto(id);
            } else {
                System.out.println("Operación cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número válido.");
        }
    }

    private static void buscarPorId() {
        try {
            int id = leerEntero("Ingrese el ID del producto que desea modificar: ");

            if (!idValido(id)) {
                System.out.println("El ID ingresado no existe.");
                return;
            }

            Producto p = Producto.getListaProducto().get(id - 1);
            System.out.println("Seleccionaste el producto con ID: " + id);

            if (confirmar("¿Querés modificar este producto? (s/n): ")) {
                modificarProducto(id - 1);
            } else {
                System.out.println("Operación cancelada.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: debe ingresar un número válido.");
            input.nextLine();
        }
    }

    private static void modificarProducto(int index) {
        Producto p = Producto.getListaProducto().get(index);

        System.out.println("Modificando producto: " + p.getNombre());

        String nombre = leerTexto("Nuevo nombre (ENTER para mantener): ").trim();

        if (!nombre.isEmpty()) {
            String nombreFormateado = FormateoDeTexto.formatearNombreProducto(nombre);
            p.setNombre(nombreFormateado);
        }

        String precioAux = leerTexto("Nuevo precio (ENTER para mantener): ").trim();
        if (!precioAux.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioAux);
                if (precio >= 0) p.setPrecio(precio);
                else System.out.println("El precio no puede ser menor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }

        String stockAux = leerTexto("Nuevo stock (ENTER para mantener): ").trim();
        if (!stockAux.isEmpty()) {
            try {
                int stock = Integer.parseInt(stockAux);
                if (stock >= 0) p.setCantStock(stock);
                else System.out.println("El stock no puede ser menor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }

        System.out.println("El producto se ha actualizado correctamente.");
    }

    public static void eliminarProducto() {
        try {
            System.out.println("""
                    Seleccione por qué método desea buscar un producto:
                    1. Buscar por Nombre.
                    2. Buscar por ID.
                    """);
            int opcion = leerEntero("Ingrese la opción: ");

            switch (opcion) {
                case 1 -> buscarEliminarPorNombre();
                case 2 -> buscarEliminarPorId();
                default -> System.out.println("Ingrese un número de los listados.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número.");
            input.nextLine();
        }
    }

    private static void buscarEliminarPorNombre() {
        String nombre = leerTexto("Ingrese el nombre del producto que desea eliminar: ");
        String nombreBuscado = FormateoDeTexto.formatearNombreProducto(nombre);

        List<Integer> coincidencias = obtenerCoincidenciasPorNombre(nombreBuscado);

        if (coincidencias.isEmpty()) {
            System.out.println("No se encontró ningún producto con ese nombre.");
            return;
        }

        mostrarCoincidencias(coincidencias);

        if (coincidencias.size() > 1) {
            eliminarDeConjuntoPorId(coincidencias);
        } else {
            confirmarYEliminar(coincidencias.get(0));
        }
    }

    private static void buscarEliminarPorId() {
        try {
            int id = leerEntero("Ingrese el ID del producto que desea eliminar: ");
            if (!idValido(id)) {
                System.out.println("El ID ingresado no existe.");
                return;
            }
            confirmarYEliminar(id - 1);
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            input.nextLine();
        }
    }

    private static void eliminarDeConjuntoPorId(List<Integer> coincidencias) {
        try {
            int id = leerEntero("Ingrese el ID del producto que desea eliminar: ");
            int index = id - 1;
            if (coincidencias.contains(index)) {
                confirmarYEliminar(index);
            } else {
                System.out.println("El ID ingresado no pertenece a los resultados mostrados.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número válido.");
            input.nextLine();
        }
    }

    private static void confirmarYEliminar(int index) {
        Producto p = Producto.getListaProducto().get(index);
        System.out.println("Seleccionaste: " + p.getNombre());
        if (confirmar("¿Querés eliminar este producto? (s/n): ")) {
            eliminarProducto(index);
        } else {
            System.out.println("Operación cancelada.");
        }
    }

    private static void eliminarProducto(int index) {
        Producto eliminado = Producto.getListaProducto().remove(index);
        System.out.println("El producto '" + eliminado.getNombre() + "' fue eliminado correctamente.");
    }

    private static boolean idValido(int id) {
        return id > 0 && id <= Producto.getListaProducto().size();
    }

    private static List<Integer> obtenerCoincidenciasPorNombre(String nombreFormateado) {
        List<Integer> coincidencias = new ArrayList<>();
        List<Producto> productos = Producto.getListaProducto();

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            if (p.getNombre().equals(nombreFormateado)) {
                coincidencias.add(i);
            }
        }
        return coincidencias;
    }

    private static void mostrarCoincidencias(List<Integer> coincidencias) {
        System.out.println("Se encontraron " + coincidencias.size() + " resultados:");
        for (int i : coincidencias) {
            Producto p = Producto.getListaProducto().get(i);
            System.out.println("ID: " + (i + 1) +
                    " | Nombre: " + p.getNombre() +
                    " | Precio: " + p.getPrecio() +
                    " | Stock: " + p.getCantStock());
        }
    }

    private static boolean confirmar(String mensaje) {
        System.out.print(mensaje);
        String opcion = input.nextLine().trim().toLowerCase();
        return opcion.equals("s") || opcion.equals("si");
    }

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return input.nextLine();
    }

    private static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = input.nextDouble();
                input.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número válido.");
                input.nextLine();
            }
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = input.nextInt();
                input.nextLine();
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Debe ingresar un número entero válido.");
                input.nextLine();
            }
        }
    }

    private static void mostrarYConfirmarModificacion(int index) {
        Producto p = Producto.getListaProducto().get(index);
        System.out.println("Se encontró 1 resultado:");
        System.out.println("ID: " + (index + 1) +
                " | Nombre: " + p.getNombre() +
                " | Precio: " + p.getPrecio() +
                " | Stock: " + p.getCantStock());

        if (confirmar("¿Querés modificar el producto? (s/n): ")) {
            modificarProducto(index);
        } else {
            System.out.println("Operación cancelada.");
        }
    }
}
