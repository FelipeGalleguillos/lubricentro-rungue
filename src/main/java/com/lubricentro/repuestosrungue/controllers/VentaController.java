package com.lubricentro.repuestosrungue.controllers;

import com.lubricentro.repuestosrungue.classes.Venta;
import com.lubricentro.repuestosrungue.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.lubricentro.repuestosrungue.dtos.CarritoDTO;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;


    @PostMapping(path= "/venta/crear")
    public ResponseEntity<Object> crearVenta(@RequestBody CarritoDTO carrito) {

        if (carrito == null) {
            ventaService.crearVentaConProductos(carrito);
            return new ResponseEntity<>("venta creada con exito",HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("El carrito esta vacio",HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping(path = "/ventas/todas")
    public List<Venta> obtenerVentas(){
        return ventaService.listarVentas();
    }

    @GetMapping(path = "/ventas/fecha")
    public List<Venta> obtenerVentasFecha(@RequestParam String desdeFecha, @RequestParam String hastaFecha){
        return ventaService.ventasEntreFecha(desdeFecha,hastaFecha);
    }


}
