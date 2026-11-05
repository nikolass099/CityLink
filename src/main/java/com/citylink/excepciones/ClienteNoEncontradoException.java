package com.citylink.excepciones;

/** Excepcion checked: se lanza al buscar un cliente que no existe */
public class ClienteNoEncontradoException extends Exception {
    /** @param mensaje descripcion del error */
    public ClienteNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}