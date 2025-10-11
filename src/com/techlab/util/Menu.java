package com.techlab.util;

public class Menu {
	public static void saludoBienvenida(){
        System.out.println("¡Bienvenido a nuestro e-commerce!");
    }

    public static void opciones(){
        System.out.println("""
                ====================================
                |        Menu Principal            |
                ====================================
                | 1.Agregar Producto               |
                | 2.Listar Productos               |
                | 3.Buscar o Actualizar Producto   |
                | 4.Eliminar Producto              |
                | 5.Realizar Pedido                |
                | 6.Listar Pedidos                 |
                | 7.Salir			   |
                ====================================
                """);
    }

    public static void saludoDespedida(){
        System.out.println("¡Gracias por utilizar nuestro sistema, nos vemos pronto!");

    }
}
