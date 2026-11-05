package com.citylink.app;

import java.util.Scanner;
import java.util.InputMismatchException;
import com.citylink.modelo.*;
import com.citylink.excepciones.*;

/*clase principal de CityLink
muestra el menu por consola y lee los datos del teclado
la logica del negocio esta en la clase CityLink, que es el gestor de los arreglos de datos
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CityLink sistema = new CityLink(); // Gestor en los arreglos de datos.
        
        // Precarga de datos para pruebas
        sistema.precargarDatos(sistema);
        System.out.println(" Sistema CityLink iniciado con datos de prueba cargados.");
        int opcion = -1;

        do {
            mostrarMenu();
            try {
                System.out.println("Seleccione una opcion: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada
                
                switch (opcion) {
                    case 1:
                        registrarCliente(scanner, sistema);
                        break;
                    case 2:
                        registrarRepartidor(scanner, sistema);
                        break;
                    case 3:
                        registrarPedido(scanner, sistema);
                        break;
                    case 4:
                        asignarPedidoARepartidor(scanner, sistema);
                        break;
                    case 5:
                        cambiarEstadoPedido(scanner, sistema);
                        break;
                    case 6:
                        buscarCliente(scanner, sistema);
                        break;
                    case 7:
                        listaPedidosPolimorfismo(sistema);
                        break;
                    case 8:
                        consultarEstadoRastreable(scanner, sistema);
                        break;
                    case 9:
                        verReporteFacturable(sistema);
                        break;
                    case 10:
                        eliminarCliente(scanner, sistema);
                        break;
                    case 11:
                        System.out.println("\n¡Gracias por utilizar el sistema CityLink!");
                        break;
                    default:
                        System.out.println("\nOpcion invalida. Ingrese un numero del 1 al 11.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError, debe ingresar un valor numero entero.");
                scanner.nextLine();
            } finally {
                System.out.println("--------------------------");
                scanner.nextLine();
            }
        } while (opcion != 11);
        scanner.close();
    }


    // Vista del Menu Principal
private static void mostrarMenu() {

        System.out.println("===========================");
        System.out.println("         CityLink          ");
        System.out.println("===========================");

        System.out.println("1.Registrar cliente.");
        System.out.println("2.Registrar repartidor con su vehiculo.");
        System.out.println("3.Registrar pedido (normal, urgente o fragil");
        System.out.println("4.Asignar un pedido a un repartidor, verificando capacidad y restricciones.");
        System.out.println("5.Cambiar el estado de un pedido (en camino, entregado, cancelado).");
        System.out.println("6.Buscar cliente por codigo o por nombre.");
        System.out.println("7.Listar pedidos con su costo y tiempo estimado (polimorfismo).");
        System.out.println("8.Consultar el estado de un pedido o de un repartidor (Rastreable).");
        System.out.println("9.Ver el importe total de los pedidos y la comision de cada repartidor (Facturable).");
        System.out.println("10. Eliminar Cliente");
        System.out.println("11. Salir.");
        System.out.println("===========================");
    }

    //Submetodos privados para cada opcion del menu.

    //opcion 1: Registrar Cliente.
    private static void registrarCliente(Scanner scanner, CityLink sistema) {
        System.out.println("\n=== Registrar Cliente. ===");
        try {
            System.out.println("Codigo unico: ");
            int cod = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Nombre completo: ");
            String nom = scanner.nextLine();
            System.out.println("Telefono: ");
            String tel = scanner.nextLine();
            System.out.println("Zona: ");
            String zona = scanner.nextLine();

            sistema.agregarCliente(new Cliente(cod, nom, tel, zona));
            System.out.println("¡Cliente registrado exitosamente!");
        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Error de almacenamiento: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos: " + e.getMessage());
        }
    }

    //Opcion 2: Registrar repartidor.

    private static void registrarRepartidor(Scanner scanner, CityLink sistema) {
        System.out.println("\n=== Registrar Repartidor. ===");
        try {
            System.out.println("Legajo: ");
            int legajo = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Nombre: ");
            String nom = scanner.nextLine();
            System.out.println("Seleccione tipo de vehiculo: 1. Bicicleta | 2. Moto | 3. Camioneta");
            int tipoV = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Patente / Identificador: ");
            String patente = scanner.nextLine();

            Vehiculo vehiculo;
            if (tipoV == 1) {
                vehiculo = new Bicicleta(patente);
            } else if (tipoV == 2) {
                vehiculo = new Moto(patente);
            } else {
                vehiculo = new Camioneta(patente);
            }

            Repartidor rep = new Repartidor(legajo, nom, vehiculo);
            sistema.agregarRepartidor(rep);
            System.out.println("¡Repartidor y vehiculo registrados exitosamente!");
        } catch (AlmacenamientoLlenoException e) {
            System.out.println("Error de almacenamiento: " + e.getMessage());
        }
    }

    //Opcion 3:Registrar pedido (normal, urgente, fragil).

    private static void registrarPedido(Scanner scanner, CityLink sistema) {
        System.out.println("\n=== Registrar Pedido. ===");
        try {
            System.out.println("Codigo unico: ");
            int cod = Integer.parseInt(scanner.nextLine());

            System.out.println("Codigo del cliente: ");
            int codigoCliente = Integer.parseInt(scanner.nextLine());

            // Buscar el cliente por código, si no se encuentra, se lanza ClienteNoEncontradoException
            Cliente cliente = sistema.buscarCliente(codigoCliente);
            
            System.out.println("Destino: ");
            String dest = scanner.nextLine();

            System.out.println("Peso en Kg: ");
            double peso = Double.parseDouble(scanner.nextLine());

            System.out.println("Distancia en Km: ");
            double distancia = Double.parseDouble(scanner.nextLine());

            System.out.println("Tipo (normal, urgente, fragil): ");
            String tipo = scanner.nextLine().toLowerCase().trim();

            Pedido pedido;
            if (tipo.equals("urgente")) {
                pedido = new PedidoUrgente(cod, cliente, dest, peso, distancia);
            } else if (tipo.equals("fragil")) {
                pedido = new PedidoFragil(cod, cliente, dest, peso, distancia);
            } else {
                pedido = new PedidoNormal(cod, cliente, dest, peso, distancia); //por defecto normal
            }

            sistema.agregarPedido(pedido);
            System.out.println("¡Pedido #" + cod + " registrado exitosamente a nombre de " + cliente.getNombre() + "!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar numeros validos en codigo, peso y distnacia.");
        } catch (ClienteNoEncontradoException | AlmacenamientoLlenoException e) {
            System.out.println("Error de negocio: " + e.getMessage());
        }
    }

    //Opcion 4: Asignar pedido a repartidor.
    private static void asignarPedidoARepartidor(Scanner scanner, CityLink sistema) {
        System.out.println("\n=== Asignar Pedido a Repartidor. ===");
        try {
            // Solicitar al usuario el código del pedido y el legajo del repartidor
            System.out.println("Codigo del pedido: ");
            int codPedido = Integer.parseInt(scanner.nextLine());

            System.out.println("Legajo del repartidor: ");
            int legajoRepartidor = Integer.parseInt(scanner.nextLine());

            //
            sistema.asignarPedido(codPedido, legajoRepartidor);
            System.out.println("¡Pedido #" + codPedido + " asignado exitosamente al repartidor con legajo " + legajoRepartidor + "!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar numeros validos en codigo y legajo.");
        } catch (Exception e) {
            System.out.println("Error de negocio: " + e.getMessage());
        }
    }

    //Opcion 5: Cambiar estado de pedido.
    private static void cambiarEstadoPedido(Scanner scanner, CityLink sistema) {
        System.out.println("\n=== Cambiar Estado de Pedido. ===");
        try {
            System.out.println("Codigo del pedido: ");
            int codPedido = Integer.parseInt(scanner.nextLine());

            System.out.println("Nuevo estado (en camino, entregado, cancelado): ");
            String nuevoEstado = scanner.nextLine().toLowerCase().trim();

            sistema.cambiarEstadoPedido(codPedido, nuevoEstado);
            System.out.println("¡Estado del pedido #" + codPedido + " cambiado exitosamente a '" + nuevoEstado + "'!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un numero valido en codigo.");
        } catch (Exception e) {
            System.out.println("Error de negocio: " + e.getMessage());
        }
    }

    //Opcion 6: Buscar cliente por codigo o nombre.
    private static void buscarCliente(Scanner scanner, CityLink sistema) {
        System.out.println("\n=== Buscar Cliente. ===");
        try {
            System.out.println("Ingrese el codigo del cliente (numero) o el nombre completo: ");
            String input = scanner.nextLine().trim();

            Cliente cliente;
            if (input.matches("\\d+")) { // Si es un numero, buscar por codigo
                int codigo = Integer.parseInt(input);
                cliente = sistema.buscarCliente(codigo);
            } else { // Si no, buscar por nombre
                cliente = sistema.buscarCliente(input);
            }

            System.out.println("Cliente encontrado: " + cliente.getNombre() + ", Telefono: " + cliente.getTelefono() + ", Zona: " + cliente.getZona());
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error de negocio: " + e.getMessage());
        }
    }


    //Opcion 7: Listar pedidos con su costo y tiempo estimado (polimorfismo).
    private static void listaPedidosPolimorfismo(CityLink sistema) {
        System.out.println("\n=== Lista de Pedidos (estimado con moto) ===");
        sistema.listaPedidosPolimorfismo();
    }


    //Opcion 8: Consultar estado de pedido o repartidor (Rastreable).
    private static void consultarEstadoRastreable(Scanner scanner, CityLink sistema) {
        System.out.println("\n=== Consultar Estado Rastreable. ===");
        try {
            System.out.println("¿Que desea consultar?");
            System.out.println("1. Estado de un pedido");
            System.out.println("2. Estado de un repartidor");
            System.out.println("Seleccione una opcion (1 o 2): ");
            int opcion = Integer.parseInt(scanner.nextLine());

            Rastreable elemntoRastreable = null; // Variable del tipo de la interfaz Rastreable para almacenar el elemento a consultar

            if (opcion == 1) {
                System.out.println("Ingrese el codigo del pedido: ");
                int cod = Integer.parseInt(scanner.nextLine());
                elemntoRastreable = sistema.buscarPedido(cod); // Asignacion polimorfica: Pedido implementa Rastreable
            } else if (opcion == 2) {
                System.out.println("Ingrese el legajo del repartidor: ");
                int legajo = Integer.parseInt(scanner.nextLine());
                elemntoRastreable = sistema.buscarRepartidor(legajo); // Asignacion polimorfica: Repartidor implementa Rastreable
            } else {
                System.out.println("Opcion invalida. Debe seleccionar 1 o 2.");
                return;
            }

            // Mostrar el estado del elemento rastreable llamando al metodo comun obtenerEstadoActual()
            System.out.println("Estado actual: "+ elemntoRastreable.obtenerEstadoActual());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un numero valido.");
        } catch (Exception e) {
            System.out.println("Error de negocio: " + e.getMessage());
        }
    }

    //Opcion 9: Ver reporte facturable.
    private static void verReporteFacturable(CityLink sistema) {
        System.out.println("\n===========================");
        System.out.println("===  Reporte Facturable  ===");
        System.out.println("============================");
        
        // Delegacion directa al gestor CityLink
        sistema.verReporteFacturable();
    }


    //Opcion 10: Eliminar cliente.
    private static void eliminarCliente(Scanner scanner, CityLink sistema) {
        try {
            System.out.println("\n=== Eliminar Cliente ===");
            System.out.println("Ingrese el codigo del cliente a eliminar: ");
            int codigo = Integer.parseInt(scanner.nextLine());
            Cliente eliminado = sistema.eliminarCliente(codigo);
            System.out.println("Cliente eliminado: " + eliminado.getNombre());
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un numero valido.");
        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error de negocio: " + e.getMessage());
        }
    }
}