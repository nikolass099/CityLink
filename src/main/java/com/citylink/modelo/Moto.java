package com.citylink.modelo;
/** Moto: carga hasta 30 kg, va a 40km/h y cuesta $120 por km. */
public class Moto extends Vehiculo {
    /** @param patente patente de la moto */
    public Moto(String patente) {
        // se asignan los valores orientativos de la consigna: 30kg, 40km/h, $120/km
        super(patente, 30, 40, 120.0);
    }

    /**    (non-Javadoc)
     * Calcula el tiempo de entrega: (distancia / velocidad) * 60 + 5 minutos extra por estacionamiento.
     * @param distanciaKm distancia en km.
     * @return tiempo de entrega en minutos.
     */
    @Override
    public double calcularTiempoEntrega(double distanciaKm) {
        // formulas de negocio: (distancia / velocidad) * 60 + min extra de estacionamieno
        return (distanciaKm / getVelocidadKmh()) * 60.0 + 5.0;
    }
}
