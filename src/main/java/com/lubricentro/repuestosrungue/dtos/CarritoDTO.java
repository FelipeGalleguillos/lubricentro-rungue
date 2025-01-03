package com.lubricentro.repuestosrungue.dtos;

import java.util.Set;

public class CarritoDTO {

    String pago;
    double total;
    Set<ProductoDTO> productos;

    public CarritoDTO() {}

    public CarritoDTO(double total, Set<ProductoDTO> productos, String pago) {
        this.total = total;
        this.productos = productos;
        this.pago = pago;
    }

    public double getTotal() {
        return total;
    }

    public Set<ProductoDTO> getProductos() {
        return productos;
    }

    public String getPago() {
        return pago;
    }
}
