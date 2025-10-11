package com.techlab.pedidos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PedidoUtils {
    private static final Scanner input = new Scanner(System.in);

    public static int leerEntero(String mensaje) {
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

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return input.nextLine().trim();
    }

    public static boolean confirmar(String mensaje) {
        System.out.print(mensaje);
        String opcion = input.nextLine().trim().toLowerCase();
        return opcion.equals("s") || opcion.equals("si");
    }
}