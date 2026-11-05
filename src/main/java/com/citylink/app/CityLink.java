package com.citylink.app;

import com.citylink.modelo.*;
import com.citylink.excepciones.*;

/*
* Gestor del sistema, guarda en arreglos los clientes (50), pedidos (100)
* y repartidores (10), y ofrece las operaciones del negocio
 */
public class CityLink {
    private static final int CAP_CLIENTS = 50;
    private static final int CAP_PEDIDOS = 100;
    private static final int CAP_REPARTIDORES = 10;

    private Cliente[] clientes = new Cliente[CAP_CLIENTS];
    private int cantClientes = 0;

    private Pedido[] pedidos = new Pedido[CAP_PEDIDOS];
    private int cantPedidos = 0;

    private Repartidor[] repartidores = new Repartidor[CAP_REPARTIDORES];
    private int cantRepartidores = 0;

    /*
     * crea un gestor con los tres arreglos vacios.
     */
    public CityLink() {
    }


/**
* Agrega un cliente al arreglo
* @param c cliente a agregar
* @throws AlmacenamientoLlenoException si se supera la capacidad maxima.
 */
    public void agregarCliente(Cliente c) throws AlmacenamientoLlenoException {
        if (cantClientes >= CAP_CLIENTS) {
            throw new AlmacenamientoLlenoException("No se puede agregar mas clientes. Capacidad máxima alcanzada (50).");
        }
        clientes[cantClientes++] = c;
    }

/**
 * Agrega un repartidor al arreglo
 * @param r repartidor a agregar
 * @throws AlmacenamientoLlenoException si se supera la capacidad maxima.
 */
    public void agregarRepartidor(Repartidor r) throws AlmacenamientoLlenoException {
        if (cantRepartidores >= CAP_REPARTIDORES) {
            throw new AlmacenamientoLlenoException("No se puede agregar mas Repartidores. Capacidad maxima alcanzada (10).");
        }
        repartidores[cantRepartidores++] = r;
    }

/**
 * Agrega un pedido al arreglo
 * @param p pedido a agregar
 * @throws AlmacenamientoLlenoException si se supera la capacidad maxima.
 */
    public void agregarPedido(Pedido p) throws AlmacenamientoLlenoException {
        if (cantPedidos >= CAP_PEDIDOS) {
            throw new AlmacenamientoLlenoException("No se puede agregar mas Pedidos. Capacidad maxima alcanzada (100).");
        }
        pedidos[cantPedidos++] = p;
    }


/**
* busca un pedido y un repartidor, y asigna el pedido al repartidor.
* @param codigoPedido codigo del pedido a asignar
* @param legajoRepartidor legajo del repartidor al que se le asigna
* @throws CapacidadExcedidaException si el repartidor ya tiene 20 pedidos asignados.
* @throws Exception si el pedido o el repartidor no se encuentran.
*/
    public void asignarPedido(int codigoPedido, int legajoRepartidor) throws CapacidadExcedidaException, Exception {
        
        // Buscar el objeto Pedido en el arreglo estatico
        Pedido pedidoHallado = null;
        for (int i = 0; i < cantPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].getCodigo() == codigoPedido) {
                pedidoHallado = pedidos[i];
                break;
            }
        }
        if (pedidoHallado == null) {
            throw new Exception("El pedido con código " + codigoPedido + " no fue encontrado.");
        }

        // buscar el objeto Repartidor en el arreglo estatico
        Repartidor repartidorHallado = null;
        for (int i = 0; i < cantRepartidores; i++) {
            if (repartidores[i] != null && repartidores[i].getLegajo() == legajoRepartidor) {
                repartidorHallado = repartidores[i];
                break;
            }
        }
        if (repartidorHallado == null) {
            throw new Exception("El repartidor con legajo " + legajoRepartidor + " no fue encontrado.");
        }

        // Asignar el pedido al repartidor
        repartidorHallado.asignarPedido(pedidoHallado);
    }


/**
* Cambia el estado de un pedido dado su código.
* @param codigoPedido código del pedido a cambiar de estado
* @param nuevoEstado nuevo estado del pedido ("pendiente", "en camino", "entregado", "cancelado")
* @throws Exception si el pedido no se encuentra o si la transición de estado no es válida.
*/

    public void cambiarEstadoPedido(int codigoPedido, String nuevoEstado) throws Exception {
        // Buscar el pedido por código
        Pedido pedidoHallado = null;
        for (int i = 0; i < cantPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].getCodigo() == codigoPedido) {
                pedidoHallado = pedidos[i];
                break;
            }
        }
        if (pedidoHallado == null) {
            throw new Exception("El pedido con código " + codigoPedido + " no fue encontrado.");
        }

        // Cambiar el estado del pedido
        pedidoHallado.cambiarEstado(nuevoEstado);
    }


    /**
     * Busca un cliente por su código y devuelve el objeto Cliente correspondiente.
     * @param codigo código del cliente a buscar
     * @return el objeto Cliente encontrado
     * @throws ClienteNoEncontradoException si no se encuentra un cliente con el código especificado
     */

    public Cliente buscarCliente(int codigo) throws ClienteNoEncontradoException {
        for (int i = 0; i < cantClientes; i++) {
            if (clientes[i] != null && clientes[i].getCodigo() == codigo) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("El cliente con código " + codigo + " no fue encontrado.");
    }

    /**
     * Busca un cliente por su nombre y devuelve el objeto Cliente correspondiente.
     * @param nombre nombre del cliente a buscar
     * @return el objeto Cliente encontrado
     * @throws ClienteNoEncontradoException si no se encuentra un cliente con el nombre especificado
     */

    public Cliente buscarCliente(String nombre) throws ClienteNoEncontradoException {
        for (int i = 0; i < cantClientes; i++) {
            if (clientes[i] != null && clientes[i].getNombre().equalsIgnoreCase(nombre.trim())) {
                return clientes[i];
            }
        }
        throw new ClienteNoEncontradoException("El cliente con nombre " + nombre + " no fue encontrado.");
    }


// Polimorfismo: muestra por consola cada pedido con su codigo, tipo, importe y estado
    public void listaPedidosPolimorfismo() {
        Vehiculo referencia = new Moto("GUG999"); // Vehiculo de referencia para calcular costos y tiempos
        double total = 0.0;
        System.out.println("=== Lista de Pedidos (estimado con moto) ===");
        for (int i = 0; i < cantPedidos; i++) {
            Pedido pedido = pedidos[i];
            double costo = pedido.calcularCosto(referencia); // cada tipo calcula distinto
            double tiempoEstimado = referencia.calcularTiempoEntrega(pedido.getDistanciaKm()); // estimado según el vehículo
            total += costo;
                System.out.println("Código: " + pedido.getCodigo() +
                        ", Tipo: " + pedido.getTipo() +
                        ", Costo: " + costo +
                        ", Tiempo Estimado: " + tiempoEstimado +
                        ", Estado: " + pedido.getEstado());
            }
        System.out.println("Total acumulado de pedidos: $" + total);
        }
    


/**
 * Busca un pedido por su código y devuelve el objeto Pedido correspondiente.
 * @param codigo código del pedido a buscar
 * @return el objeto Pedido encontrado
 * @throws Exception si no se encuentra un pedido con el código especificado
 */

    public Pedido buscarPedido(int codigo) throws Exception {
        for (int i = 0; i < cantPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].getCodigo() == codigo) {
                return pedidos[i];
            }
        }
        throw new Exception("El pedido con código " + codigo + " no fue encontrado.");
    }

/**
 * Busca un repartidor por su legajo y devuelve el objeto Repartidor correspondiente.
 * @param legajo legajo del repartidor a buscar
 * @return el objeto Repartidor encontrado
 * @throws Exception si no se encuentra un repartidor con el legajo especificado
 */
    public Repartidor buscarRepartidor(int legajo) throws Exception {
        for (int i = 0; i < cantRepartidores; i++) {
            if (repartidores[i] != null && repartidores[i].getLegajo() == legajo) {
                return repartidores[i];
            }
        }
        throw new Exception("El repartidor con legajo " + legajo + " no fue encontrado.");
    }


// Se elimina el lemento y se corren los elemntos siguientes una posiscion para dejar el arreglo sin espacios vacios.
    public Cliente eliminarCliente(int codigo) throws ClienteNoEncontradoException {
        int indice = -1;
        for (int i = 0; i < cantClientes; i++) {
            if (clientes[i] != null && clientes[i].getCodigo() == codigo) {
                indice = i;
                break;
            }
        }
        if (indice == -1) {
            throw new ClienteNoEncontradoException("No se encontro el cliente que desea eliminar.");
        }

        Cliente eliminado = clientes[indice];

        // Dezplazamiento hacia la izquierda
        for (int i = indice; i < cantClientes - 1; i++) {
            clientes[i] = clientes[i + 1];
        }
        clientes[--cantClientes] = null; // Limpieza de la ultima posicion.
        return eliminado;
    }

    // Polimorfismo: Calculo sobre arreglo heterogeneo
    public void verReporteFacturable() {
        System.out.println("\n=== 1. Facturacion total de pedidos ===");
        double totalPedidos = 0.0;

        for (int i = 0; i < cantPedidos; i++) {
            if (pedidos[i] != null) {
                // Invocacion a la interfaz Facturable en Pedido
                totalPedidos += pedidos[i].calcularImporte();
            }
        }
        System.out.println("El monto total acumulado por envios: $" + totalPedidos);

        System.out.println("\n=== 2. Comision de repartidor (10%) ===");
        if (cantRepartidores == 0) {
            System.out.println("No hay repartidores registrados en el sistema.");
        } else {
            for (int i = 0; i < cantRepartidores; i++) {
                if (repartidores[i] != null) {
                    // Invocacion a la interfaz Facturable en Repartidor
                    double comision = repartidores[i].calcularImporte();
                    System.out.println("Repartidor: " + repartidores[i].getNombre() +
                                        ", Legajo #" + repartidores[i].getLegajo() +
                                        ", Comision total acumulada: $" + comision);
                }
            }
        }
    }


        // datos precargados.
    public void precargarDatos(CityLink sistema) {
        try {
            // 1. Precarga de 5 clientes.
            Cliente c1 = new Cliente(1, " Juliana Allende", "2657717111", "centro");
            Cliente c2 = new Cliente(2, "Jose Ruiz", "2657546723", "Atte 2");
            Cliente c3 = new Cliente(3, "Pedro Ruiz", "2657890123", "Norte");
            Cliente c4 = new Cliente(4, "Claudia Mercado", "2657123456", "Sur");
            Cliente c5 = new Cliente(5, "Juan Perez", "2657654321", "Ribera");

            agregarCliente(c1);
            agregarCliente(c2);
            agregarCliente(c3);
            agregarCliente(c4);
            agregarCliente(c5);

            // 2. Precarga de 3 repartidores con sus vehiculos.
            Vehiculo bici = new Bicicleta("BIC877");
            Vehiculo moto = new Moto("MOT123");
            Vehiculo camioneta = new Camioneta("CAM456");

            Repartidor r1 = new Repartidor(101, "Pedro Martinez", bici);
            Repartidor r2 = new Repartidor(102, "Lucia Fernandez", moto);
            Repartidor r3 = new Repartidor(103, "Diego Torres", camioneta);

            agregarRepartidor(r1);
            agregarRepartidor(r2);
            agregarRepartidor(r3);

            // 3. Precarga de 8 Pedidos (Normales, Urgentes y Fragiles)
            Pedido p1 = new PedidoNormal(501, c1, "Calle Pedernera 123", 3.0, 4.0);
            Pedido p2 = new PedidoUrgente(502, c2, "Calle San Martin 456", 1.5, 8.0);
            Pedido p3 = new PedidoFragil(503, c3, "Calle Mitre 789", 2.0, 6.0);
            Pedido p4 = new PedidoNormal(504, c4, "Calle Rivadavia 321", 4.0, 10.0);
            Pedido p5 = new PedidoUrgente(505, c5, "Calle Belgrano 654", 0.5, 12.0);
            Pedido p6 = new PedidoFragil(506, c1, "Calle San Juan 987", 1.0, 5.0);
            Pedido p7 = new PedidoNormal(507, c2, "Calle Sarmiento 246", 2.5, 7.0);
            Pedido p8 = new PedidoUrgente(508, c3, "Calle Rosales 135", 3.5, 9.0);

            agregarPedido(p1);
            agregarPedido(p2);
            agregarPedido(p3);
            agregarPedido(p4);
            agregarPedido(p5);
            agregarPedido(p6);
            agregarPedido(p7);
            agregarPedido(p8);


            // 4. Asignacion de prueba iniciales

            asignarPedido(501, 101); // Pedido 501 a Repartidor 101
            asignarPedido(503, 102); // Pedido 503 a Repartidor 102

        } catch (AlmacenamientoLlenoException | CapacidadExcedidaException e) {
            System.out.println("Error durante la precarga inicial: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error general en precarga: " + e.getMessage());
        }
    }
}
