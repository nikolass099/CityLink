package com.citylink.modelo;

/** Pedido normal: sin recargo. */

public class PedidoNormal extends Pedido {

    /**
     * Constructor para crear un pedido normal.
     * @param codigo código del pedido
     * @param cliente cliente del pedido
     * @param destino destino del pedido
     * @param pesoKg peso del pedido en kg
     * @param distanciaKm distancia del pedido en km
     */
    public PedidoNormal(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm) {
        super(codigo, cliente, destino, pesoKg, distanciaKm, "normal");
    }

    /**
     * Calcula el costo del pedido normal.
     * @param vehiculo vehículo utilizado para el pedido
     * @return costo del pedido
     */
    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        // Calcula el costo base del pedido normal
        return 1000.0 + (getDistanciaKm() * vehiculo.getCostoPorKm());
    }
}
