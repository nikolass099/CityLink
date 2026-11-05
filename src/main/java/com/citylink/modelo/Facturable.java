package com.citylink.modelo;

/**
 * Objeto que genera un importe de dinero
 * la implementan Pedido (costo del envio) y Repartidor (comision).
 */
public interface Facturable {
    /** @return el importe total del objeto facturable */
    double calcularImporte();
}
