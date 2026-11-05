package com.citylink.excepciones;

/** Excepcion checked: se lanza al agregar a un arreglo que ya esta lleno */
public class AlmacenamientoLlenoException extends Exception {
    /** @param mensaje descripcion del error */
    public AlmacenamientoLlenoException(String mensaje) {
        super(mensaje);
    }
}
