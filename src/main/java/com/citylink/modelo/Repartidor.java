package com.citylink.modelo;

import com.citylink.excepciones.CapacidadExcedidaException;
import com.citylink.excepciones.PedidoInvalidoException;

/**
 * Clase que representa un repartidor en el sistema de CityLink.
 * Implementa las interfaces Rastreable (su estado) y Facturable (su comision).
 */
public class Repartidor implements Rastreable, Facturable {
    // Atributos privados para el encapsulamiento

    private int legajo;
    private String nombre;
    private Vehiculo vehiculo;
    private Pedido[] pedidos;
    private int cantPedidos;

    /**
     * Crea un repartidor vacio, con espacio para 20 pedidos.
     */
    public Repartidor() {
        this.pedidos = new Pedido[20];
        this.cantPedidos = 0;
    }

    /**
     * Crea un repartidor con legajo, nombre y vehiculo especificados, con espacio para 20 pedidos.
     * @param legajo numero de legajo del repartidor (mayor a 0)
     * @param nombre nombre del repartidor (no nulo)
     * @param vehiculo vehiculo asignado al repartidor (no nulo)
     */
    public Repartidor(int legajo, String nombre, Vehiculo vehiculo) {
        setLegajo(legajo);
        setNombre(nombre);
        setVehiculo(vehiculo);
        this.pedidos = new Pedido[20];
        this.cantPedidos = 0;
    }


    public int getLegajo() {
        return legajo;
    }
    public void setLegajo(int legajo) {
        if (legajo <= 0){
            throw new  IllegalArgumentException("El legajo del Repartidor debe ser mayor a 0.");
        } this.legajo = legajo;
    }


    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre == null) {
            throw new IllegalArgumentException("El Repartidor debe tener nombre.");
        } this.nombre = nombre;
    }


    public Vehiculo getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(Vehiculo vehiculo) {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El Repartidor debe tener un Vehiculo.");
        } this.vehiculo = vehiculo;
    }

/**
 * Asigna un pedido al repartidor, verificando que el vehiculo pueda transportarlo y que no se exceda la capacidad de pedidos.
 * @param pedido
 * @throws CapacidadExcedidaException
 */
    public void asignarPedido(Pedido pedido) throws CapacidadExcedidaException {
        asignarPedido(pedido, this.vehiculo);
    }

    public void asignarPedido(Pedido pedido, Vehiculo vehiculo) throws CapacidadExcedidaException {
        if (vehiculo == null) {
            throw new IllegalArgumentException("El Repartidor no tiene un vehiculo asignado");
        }
        if (!vehiculo.puedeTransportar(pedido)) {
            throw new CapacidadExcedidaException("El Vehiculo " + vehiculo.getPatente() + " no puede llevar este pedido.");
        }
        if (cantPedidos >= pedidos.length) {
            throw new CapacidadExcedidaException("La mochila de pedidos esta llena");
        }
        this.pedidos[cantPedidos] = pedido;
        this.cantPedidos++; //avvanca el contador de paquetes ocupados.
    }


/**
 * Registra la entrega de un pedido dado su código, cambiando su estado a "entregado".
 * @param codigoPedido
 * @throws PedidoInvalidoException
 */
    public void registrarEntrega(int codigoPedido) throws PedidoInvalidoException {
        boolean encontrado = false;

        for (int i = 0; i < cantPedidos; i++) {
            if (pedidos[i] != null && pedidos[i].getCodigo() == codigoPedido) {
                pedidos[i].cambiarEstado("entregado");
                encontrado = true;
                System.out.println("¡Entrega realizada con exito para el pedido #" + codigoPedido + "!");
                break;
            }
        }

        // Si termino el ciclo y no lo hallo en su mochila, se laza la excepcion
        if (!encontrado) {
            throw new PedidoInvalidoException("El Repartidor " + nombre + " no tiene a cargo el pedido #" + codigoPedido);
        }
    }

/**
 * @return texto que indica si el repartidor esta disponible o cuantos pedidos tiene a cargo.
 */
    @Override
    public String obtenerEstadoActual() {
        if (cantPedidos == 0) {
            return "Repartidor " + nombre + " disponible.";
        }else {
            return "Repartidor " + nombre + " con " + cantPedidos + " pedidos a cargo)";
        }
    }

    /**
     * Calcula la comision: 10% del importe de los pedidos entregados por el repartidor.
     * @return importe de la comision del repartidor
     */
    public double calcularImporte() {
        double suma = 0.0;
        for (int i = 0; i < cantPedidos; i++) {
            // ¿Hay paquete? y ¿Dice "Entregado"?
            if (pedidos[i] != null &&
                "entregado".equals(pedidos[i].obtenerEstadoActual())) {
                    // Le sumamos el costo de este paquere a la billetera
                    suma += pedidos[i].calcularImporte() * 0.10;
            }
        }
        return suma;
    }
}
