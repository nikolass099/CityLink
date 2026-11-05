package com.citylink.modelo;

/** Bicicleta: carga hasta 10 kg, va a 15km/h y cuesta $50 por km. */
public class Bicicleta extends Vehiculo {

    /** @param patente patente de la bicicleta */

    public Bicicleta(String patente) {
        // se asignan los valores orientativos de la consigna: 10kg, 15km/h, $50/km
        super(patente, 10, 15, 50.0);
    }

    /**
     * Calcula si la bicicleta puede transportar un pedido fragil.
     * @param pedido pedido a transportar
     * @return true si la bicicleta puede transportar el pedido, false en caso contrario
     */
    @Override
    public boolean puedeTransportar(Pedido pedido) {
        // Se verifica si el pedido es urgente y si la bicicleta puede transportarlo
        if ("fragil".equals(pedido.getTipo())) {
            return false; // La bicicleta no puede transportar pedidos fragiles
        }
        return super.puedeTransportar(pedido);
    }

/**
Calcula el tiempo de entrega: (distancia / velocidad) * 60.
* @param distanciaKm distancia en km.
* @return tiempo de entrega en minutos.
*/
    @Override
    public double calcularTiempoEntrega(double distanciaKm) {
        return (distanciaKm / getVelocidadKmh()) * 60.0;
    }
}

