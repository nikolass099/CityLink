package com.citylink.modelo;

/**
 * objeto cuyo estado se puede consultar
 * la implementan Pedido y Repartidor
 */
public interface Rastreable {
    /** @return texto que indica el estado actual del objeto */
    String obtenerEstadoActual();
}
