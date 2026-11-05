package com.citylink.modelo;
/** Pedido fragil: lleva un recargo fijo de $800 por cuidado especial.  */
public class PedidoFragil extends Pedido {
    /**
     * Constructor de la clase PedidoFragil que inicializa un pedido frágil con los mismos parametros que Pedidonormal.
     * @param codigo
     * @param cliente
     * @param destino
     * @param pesoKg
     * @param distanciaKm
     */
    public PedidoFragil(int codigo, Cliente cliente, String destino, double pesoKg, double distanciaKm) {
        super(codigo, cliente, destino, pesoKg, distanciaKm, "fragil");
    }
/** Calcula el costo del pedido frágil, incluyendo un recargo adicional. */
    @Override
    public double calcularCosto(Vehiculo vehiculo) {
        // Calcula el costo base del pedido frágil
        double costoBase = 1000.0 + (getDistanciaKm() * vehiculo.getCostoPorKm());
        // Aplica un recargo adicional de 800.0 para pedidos frágiles
        return costoBase + 800.0;
    }
}