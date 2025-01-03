package com.lubricentro.repuestosrungue.implementations;

import com.lubricentro.repuestosrungue.classes.Producto;
import com.lubricentro.repuestosrungue.repositories.ProductoRepository;
import com.lubricentro.repuestosrungue.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductoRepository productoRepository;


    @Override
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> obtenerProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto obtenerProductoPorBarcode(String barcode) {
        return productoRepository.findByBarcode(barcode);
    }

    @Override
    public void eliminarProducto(Producto producto) {
        productoRepository.delete(producto);
    }
}
