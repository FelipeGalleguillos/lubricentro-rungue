package com.lubricentro.repuestosrungue.classes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lubricentro.repuestosrungue.classes.Producto;
import com.lubricentro.repuestosrungue.classes.Venta;
import jakarta.persistence.*;


@Entity
public class VentaProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Relación con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id")  // Nombre de la columna de la relación
    private Producto producto;
    // Relación con Venta
    @ManyToOne
    @JoinColumn(name = "venta_id")  // Nombre de la columna de la relación
    private Venta venta;
    private int cantidad;

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @JsonIgnore
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}