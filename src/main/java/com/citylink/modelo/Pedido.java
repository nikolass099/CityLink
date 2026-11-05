package com.citylink.modelo;

import java.time.LocalDateTime;
import com.citylink.excepciones.EstadoInvalidoException;

/**
 * Clase abstracta que representa un pedido de envio
 * Implementa las interfaces Rastreable (consulta su estado) y Facturable (calcula su importe)
 * Sus hijas PedidoNormal, PedidoUrgente y PedidoFragil definen el costo.
 */

public abstract class Pedido implements Rastreable, Facturable {
    // atributos privados para encapsular la información del pedido
    private int codigo;
    private Cliente cliente;
    private String destino;
    private double pesoKg;
    private double distanciaKm;
    private LocalDateTime fechaHoraSolicitud = LocalDateTime.now(); // Variable final para almacenar la fecha y hora de solicitud del pedido
    private String estado;
    private String tipo;

    public LocalDateTime getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    //contrusctor vacio (sin parametros)
    public Pedido() {
    }

    /**
     * Crea un pedido nuevo con estado "pendiente".
     * 
     * @param codigo código unico del pedido (mayor a 0)
     * @param cliente cliente que lo solicita (no nulo)
     * @param destino destino del pedido (no vacio)
     * @param pesoKg peso del pedido en kg (mauyor a 0)
     * @param distanciaKm distancia del pedido en km (mauyor a 0)
     * @param tipo tipo del pedido "normal", "urgente" o "fragil" (no nulo)
     * @throws IllegalArgumentException si alguno de los parametros no cumple con las condiciones
     */
    public Pedido(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm, String tipo) {
        setCodigo(codigo);
        setCliente(cliente);
        setDestino(destino);
        setPesoKg(pesoKg);
        setDistanciaKm(distanciaKm);
        setTipo(tipo);
        this.estado = "pendiente";
    }

    /**
     * Calcula el costo del pedido según el tipo y el vehículo utilizado.
     * @param vehiculo vehículo utilizado para el envío
     * @return costo del pedido
     */
    public abstract double calcularCosto(Vehiculo vehiculo);

    /**
     * Cambia el estado validando la transicion:
     * "pendiente" pasa a "en camino" o "cancelado", y "en camino" pasa a "entregado".
     * @param nuevoEstado estado al que se quiere cambiar el pedido
     * @throws EstadoInvalidoException si la transicion no es valida
     */

    public void cambiarEstado(String nuevoEstado) {
        if (this.estado.equals("pendiente") && (nuevoEstado.equals("en camino")
            || nuevoEstado.equals("cancelado"))) {
        this.estado = nuevoEstado;
    } else if (this.estado.equals("en camino") &&
nuevoEstado.equals("entregado")) {
    this.estado = nuevoEstado;
    } else {
        throw new EstadoInvalidoException("Transicion invalida de " + this.estado + " a " + nuevoEstado);
        // Unchecked
}
    }
    /**
     * Obtiene el codigo del pedido
     * @return codigo del pedido
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Establece el codigo del pedido
     * @param codigo codigo del pedido (mayor a 0)
     * @throws IllegalArgumentException si el codigo es menor o igual a 0
     */
    public void setCodigo(int codigo) {
        if(codigo <= 0) {
            throw new IllegalArgumentException("El codigo del pedido debe ser mayor a 0");
        }
        this.codigo = codigo;
    }

    /**
     * Obtiene el cliente del pedido
     * @return cliente del pedido
     */
    public Cliente getCliente() {
        return cliente;
    }
    /**
     * Establece el cliente del pedido
     * @param cliente cliente del pedido
     * @throws IllegalArgumentException si el cliente es nulo
     */
    public void setCliente(Cliente cliente) {
        if(cliente == null) {
            throw new IllegalArgumentException("El cliente del pedido no puede ser nulo");
        }
        this.cliente = cliente;
    }

    /**
     * Obtiene el destino del pedido
     * @return destino del pedido
     */
    public String getDestino() {
        return destino;
    }

    /**
     * establece el destino del pedido
     * @param destino
     * @throws IllegalArgumentException si el destino es nulo o vacio
     */
    public void setDestino(String destino) {
        if (destino == null || destino.trim().isEmpty()) {
            throw new IllegalArgumentException("El destino del pedido no puede estar vacío");
        }
        this.destino = destino;
    }

    /**
     * Obtiene el peso del pedido
     * @return peso del pedido
     */
    public double getPesoKg() {
        return pesoKg;
    }

    /**
     * Establece el peso del pedido
     * @param pesoKg peso del pedido
     * @throws IllegalArgumentException si el peso es menor o igual a 0
     */
    public void setPesoKg(double pesoKg) {
        if (!Double.isFinite(pesoKg) || pesoKg <= 0) {
            throw new IllegalArgumentException("El peso del pedido debe ser un número mayor a 0.");
        }
        this.pesoKg = pesoKg;
    }

    /**
     * Obtiene la distancia del pedido
     * @return distancia del pedido
     */
    public double getDistanciaKm() {
        return distanciaKm;
    }

    /**
     * Establece la distancia del pedido
     * @param distanciaKm distancia del pedido
     * @throws IllegalArgumentException si la distancia es menor o igual a 0
     */
    public void setDistanciaKm(double distanciaKm) {
        if (!Double.isFinite(distanciaKm) || distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser un número mayor a 0.");
        }
        this.distanciaKm = distanciaKm;
    }

    /**
     * Obtiene el estado del pedido
     * @return estado del pedido
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Obtiene el tipo del pedido
     * @return tipo del pedido
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del pedido
     * @param tipo tipo del pedido
     * @throws IllegalArgumentException si el tipo es nulo
     */
    public void setTipo(String tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de pedido no puede ser nulo.");
        }
        String tipoNormalizado = tipo.trim().toLowerCase();
        if ("normal".equals(tipoNormalizado)
                || "urgente".equals(tipoNormalizado)
                || "fragil".equals(tipoNormalizado)) {
            this.tipo = tipoNormalizado;
        } else {
            throw new IllegalArgumentException("Tipo de pedido no válido.");
        }
    }

    /**
     * Obtiene el estado actual del pedido
     * @return estado actual del pedido
     */
    public String obtenerEstadoActual() {
        return this.estado;
    }


    /**
     * Actualiza el estado del pedido
     * @param nuevoEstado nuevo estado del pedido
     * @throws IllegalArgumentException si el estado es nulo o vacío
     */
    public void actualizarEstado(String nuevoEstado) {
        if (nuevoEstado == null || nuevoEstado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado del pedido no puede estar vacío");
        }
        this.estado = nuevoEstado;
    }

    /**
     * Calcula el importe del pedido
     * @return importe del pedido
     */
    @Override
    public double calcularImporte() {
        return 1000.0 + (getDistanciaKm() * 100.0);
    }

    /**
     * Devuelve una representación en cadena del pedido
     * @return representación en cadena del pedido
     */

    @Override
    public String toString() {
        return "pedido [codigo=" + codigo +
                ", cliente=" + cliente.getNombre() +
                ", destino=" + destino +
                ", pesoKg=" + pesoKg +
                ", distanciaKm=" + distanciaKm +
                ", fechaHoraSolicitud=" + fechaHoraSolicitud +
                ", estado=" + estado +
                ", tipo=" + tipo + "]";

    }
}