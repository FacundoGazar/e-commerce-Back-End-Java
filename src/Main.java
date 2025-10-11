import java.util.InputMismatchException;
import java.util.Scanner;

import com.techlab.pedidos.PedidoService;
//import com.techlab.excepciones.NombreDeProductoVacioExcepcion;
import com.techlab.productos.ProductoService;
import com.techlab.util.Menu;


public class Main {

    public static void main(String[] args){

        Scanner input = new Scanner(System.in);
        boolean verMenu = true;

        Menu.saludoBienvenida();

        while(verMenu){

            Menu.opciones();
            System.out.print("Seleccione una opción:");

            try{
                int opcion = input.nextInt();
                switch (opcion) {
                    case 1 -> ProductoService.agregar();
                    case 2 -> ProductoService.listar();
                    case 3 -> ProductoService.buscarProducto();
                    case 4 -> ProductoService.eliminarProducto();
                    case 5 -> PedidoService.crearPedido();
                    case 6 -> PedidoService.listarPedidos();
                    case 7->{
                        Menu.saludoDespedida();
                        verMenu = false;
                    }
                    default -> System.out.println("Ingrese un número de los listados.");
                }
            }catch(InputMismatchException e){
                    System.out.println("Error: debe ingresar un número.");
                    input.nextLine();
                }

            input.nextLine();

            }

        }

    }