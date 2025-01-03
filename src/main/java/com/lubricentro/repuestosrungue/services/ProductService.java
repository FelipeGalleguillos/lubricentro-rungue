package com.lubricentro.repuestosrungue.services;

import com.lubricentro.repuestosrungue.classes.Producto;

import java.util.List;

public interface ProductService {

    void guardarProducto(Producto producto);

    List<Producto> obtenerProductos();

    Producto obtenerProductoPorBarcode(String barcode);

    void eliminarProducto(Producto producto);

}
