package com.citylink.excepciones;

/* Excepcion checked: se lanza cuando un pedido es invalido. */
public class PedidoInvalidoException extends Exception {
    /** @param mensaje descripcion del error. */
    public PedidoInvalidoException(String mensaje){
        super(mensaje);
    }
}
