package com.citylink.modelo;

/**
 * Clase que representa un cliente de la empresa Citylink que solicita envios.
 * guarda su codigo, nombre, telefono y zona
 */
public class Cliente {
    private int codigo;
    private String nombre;
    private String telefono;
    private String zona;

    /** contructor vacio (sin parametros) */
    public Cliente() {
    }

    /**
     * Crea un cliente validando cada dato con sus setters
     * @param codigo codigo unico del cliente (mayor a 0)
     * @param nombre nombre del cliente (no vacio)
     * @param telefono telefono del cliente (no vacio)
     * @param zona zona del cliente (no vacio)
     */

    public Cliente(int codigo, String nombre, String telefono, String zona) {
        setCodigo(codigo);
        setNombre(nombre);
        setTelefono(telefono);
        setZona(zona);
    }

    /**
     * @return codigo del cliente
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * cambia el codigo del cliente validando que sea mayor a 0
     * @param codigo codigo del cliente (mayor a 0)
     * @throws IllegalArgumentException si el codigo es menor o igual a 0
     */
    public void  setCodigo(int codigo) {
        if (codigo <= 0) {
            throw new IllegalArgumentException("El codigo del cliente debe ser mayor a 0");
        }
        this.codigo = codigo;
    }

    /**
     * Obtiene el nombre del cliente
     * @return nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * cambia el nombre del cliente validando que no sea nulo ni vacío
     * @param nombre nombre del cliente (no vacio)
     * @throws IllegalArgumentException si el nombre es nulo o vacío
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacío");
        this.nombre = nombre;
    }

    /**
     * Obtiene el telefono del cliente
     * @return telefono del cliente
     */
    public String getTelefono() {
        return telefono;
    }
    /**
     * cambia el telefono del cliente validando que no sea nulo ni vacío
     * @param telefono telefono del cliente (no vacio)
     * @throws IllegalArgumentException si el telefono es nulo o vacío
     */
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El telefono del cliente no puede estar vacío");
        }
        this.telefono = telefono;
    }

    /**
     * Obtiene la zona del cliente
     * @return zona del cliente
     */
    public String getZona() {
        return zona;
    }

    /**
     * cambia la zona del cliente validando que no sea nula ni vacía
     * @param zona zona del cliente (no vacia)
     * @throws IllegalArgumentException si la zona es nula o vacía
     */
    public void setZona(String zona) {
        if (zona == null || zona.trim().isEmpty()) {
            throw new IllegalArgumentException("La zona del cliente no puede estar vacía");
        }
        this.zona = zona;
    }
    /**
     * @return texto con los datos del cliente
     */
    @Override
    public String toString() {
        return "Cliente [codigo=" + codigo +
                ", nombre=" + nombre +
                ", telefono=" + telefono +
                ", zona=" + zona + "]";
    }
}
