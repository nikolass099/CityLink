package com.citylink.modelo;

/** Pedido urgente: con recargo del 50%. */
public class PedidoUrgente extends Pedido {

    /**
     * Constructor para crear un pedido urgente.
     * @param codigo código del pedido
     * @param cliente cliente del pedido
     * @param destino destino del pedido
     * @param pesoKg peso del pedido en kg
     * @param distanciaKm distancia del pedido en km
     */
    public PedidoUrgente(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm) {
        super(codigo, cliente, destino, pesoKg, distanciaKm, "urgente");
    }

    /**
     * Calcula el costo base y le suma el %50 de recargo por urgencia.
     * @param vehiculo vehículo utilizado para el pedido
     * @return costo del pedido
     */
    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        double costoBase = 1000.0 + (getDistanciaKm() * vehiculo.getCostoPorKm());
        
        return costoBase * 1.5;
    }
}

