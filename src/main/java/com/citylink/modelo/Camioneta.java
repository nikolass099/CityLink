package com.citylink.modelo;

/** Camioneta: carga hasta 500 kg, va a 30km/h y cuesta $300 por km. */
public class Camioneta extends Vehiculo {

    /** @param patente patente de la camioneta.
    */

    public Camioneta(String patente) {
        // se asignan los valores orientativos de la consigna: 500kg, 30km/h, $300/km
        super(patente, 500, 30, 300.0);
    }

    /** 
     * Calcula el tiempo de entrega: (distancia / velocidad) * 60 + 10 minutos extra por carga y descarga.
     * @param distanciaKm distancia en km.
     * @return tiempo de entrega en minutos.
     */
    @Override
    public double calcularTiempoEntrega(double distanciaKm) {
        // formula de negocio: (distancia / velocidad) * 60 + 10 min extra por carga y descarga
        return (distanciaKm / getVelocidadKmh()) * 60.0 + 10.0;
    }
}
