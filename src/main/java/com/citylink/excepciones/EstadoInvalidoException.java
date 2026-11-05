package com.citylink.excepciones;

/** Excepcion unchecked: se lanza cuando se intenta establecer un estado invalido */
public class EstadoInvalidoException extends RuntimeException {
    /** @param mensaje descripcion del error */
    public EstadoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
