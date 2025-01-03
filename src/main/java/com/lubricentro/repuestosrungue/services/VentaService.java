package com.lubricentro.repuestosrungue.services;

import com.lubricentro.repuestosrungue.classes.Venta;
import com.lubricentro.repuestosrungue.dtos.CarritoDTO;

import java.util.List;
import java.util.Set;

public interface VentaService {

    void crearVentaConProductos(CarritoDTO carrito);

    void eliminarVenta(Venta venta);

    List<Venta> listarVentas();

    List<Venta> ventasEntreFecha(String desdeFecha, String hastaFecha);
}
