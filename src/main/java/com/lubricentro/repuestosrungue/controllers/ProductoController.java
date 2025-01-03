package com.lubricentro.repuestosrungue.controllers;

import com.lubricentro.repuestosrungue.classes.Producto;
import com.lubricentro.repuestosrungue.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductoController {


    @Autowired
    ProductService productService;


    @GetMapping("/productos")
    public List<Producto> getProductos() {
        return productService.obtenerProductos();
    }

    @GetMapping("/producto{barcode}")
    public Producto getProductoActual(@PathVariable String barcode) {
        return productService.obtenerProductoPorBarcode(barcode);
    }

    @PostMapping("/producto/ingresar")
    ResponseEntity<Object> ingresarProducto(@RequestParam String barcode, @RequestParam String nombre, @RequestParam String categoria,@RequestParam int stock, @RequestParam double precio) {

        if (productService.obtenerProductoPorBarcode(barcode) == null) {
            productService.guardarProducto(new Producto(barcode, nombre, categoria, stock, precio));
            return new ResponseEntity<>("producto creado",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("El producto ya existe",HttpStatus.CONFLICT);
        }

    }
}
