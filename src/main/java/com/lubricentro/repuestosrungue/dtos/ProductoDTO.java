package com.lubricentro.repuestosrungue.dtos;

public class ProductoDTO {

    private String barcode;
    private int cantidad;

    public ProductoDTO() {}

    public ProductoDTO(String barcode, int cantidad) {
        this.barcode = barcode;
        this.cantidad = cantidad;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getCantidad() {
        return cantidad;
    }
}
