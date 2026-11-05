package com.citylink.excepciones;

/** Excepcion checked: se lanza cuando un vehiculo no puede llevar un pedido o la mochila esta llena */
public class CapacidadExcedidaException extends Exception {
    /** @param mensaje descripcion del error */
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}
