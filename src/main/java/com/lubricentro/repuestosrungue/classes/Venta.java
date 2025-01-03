package com.lubricentro.repuestosrungue.classes;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.HashSet;



@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String pago;
    private double total;
    private LocalDateTime fecha;

    @OneToMany(mappedBy = "venta",cascade = CascadeType.ALL, orphanRemoval = true)  // Relación uno a muchos con VentaProducto
    private Set<VentaProducto> ventasProductos = new HashSet<>();

    public Venta() {}

    public Venta(Long id, String pago, double total, LocalDateTime fecha) {
        this.id = id;
        this.pago = pago;
        this.total = total;
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFecha() {return fecha;}

    public void setFecha(LocalDateTime fecha) {this.fecha = fecha;}

    public Set<VentaProducto> getVentasProductos() {
        return ventasProductos;
    }

    public void setVentasProductos(Set<VentaProducto> ventasProductos) {
        this.ventasProductos = ventasProductos;
    }
}
