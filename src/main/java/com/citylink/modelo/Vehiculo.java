package com.citylink.modelo;

/**
 * Clase abstracta que representa un vehiculo de reparto
 * de ella heredan Bicicleta, Moto y Camioneta
 */

public abstract class Vehiculo {

    // encapsulamiento con modificador private
    private String patente;
    private double capacidadKg;
    private double velocidadKmh;
    private double costoPorKm;

    /**
     * Constructor de la clase Vehiculo
     * @param patente patente del vehiculo
     * @param capacidadKg capacidad del vehiculo en kg
     * @param velocidadKmH velocidad del vehiculo en km/h
     * @param costoPorKm costo del vehiculo por km
     */

    public Vehiculo(String patente, double capacidadKg, double velocidadKmH, double costoPorKm) {
        this.patente = patente;
        this.capacidadKg = capacidadKg;
        this.velocidadKmh = velocidadKmH;
        this.costoPorKm = costoPorKm;
    }

    /**
     * Calcula el tiempo de entrega para el vehiculo
     * @param distanciaKM distancia en km
     * @return tiempo de entrega en minutos
     */
    public abstract double calcularTiempoEntrega(double distanciaKM);

    /**
     * Verifica si el vehiculo puede transportar un pedido
     * @param pedido pedido a transportar
     * @return true si el vehiculo puede transportar el pedido, false en caso contrario
     */
    public boolean puedeTransportar(Pedido pedido) { // metodo base
        return pedido.getPesoKg() <= this.capacidadKg;
    }

    /**
     * Obtiene la patente del vehiculo
     * @return patente del vehiculo
     */
    public String getPatente() {
        return patente;
    }
    /**
     * Obtiene la capacidad del vehiculo en kg
     * @return capacidad del vehiculo en kg
     */
    public double getCapacidadKg() {
        return capacidadKg;
    }

    /**
     * Obtiene la velocidad del vehiculo en km/h
     * @return velocidad del vehiculo en km/h
     */
    public double getVelocidadKmh() {
        return velocidadKmh;
    }

    /**
     * Obtiene el costo del vehiculo por km
     * @return costo del vehiculo por km
     */
    public double getCostoPorKm() {
        return costoPorKm;
    }
}
