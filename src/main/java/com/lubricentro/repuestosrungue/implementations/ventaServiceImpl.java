package com.lubricentro.repuestosrungue.implementations;

import com.lubricentro.repuestosrungue.classes.Producto;
import com.lubricentro.repuestosrungue.classes.Venta;
import com.lubricentro.repuestosrungue.classes.VentaProducto;
import com.lubricentro.repuestosrungue.dtos.CarritoDTO;
import com.lubricentro.repuestosrungue.dtos.ProductoDTO;
import com.lubricentro.repuestosrungue.repositories.ProductoRepository;
import com.lubricentro.repuestosrungue.repositories.VentaProductoRepository;
import com.lubricentro.repuestosrungue.repositories.VentaRepository;
import com.lubricentro.repuestosrungue.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ventaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private VentaProductoRepository ventaProductoRepository;
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void crearVentaConProductos(CarritoDTO carro) {
        Set<ProductoDTO> productosDTO = carro.getProductos();
        Set<Producto> productos = new HashSet<>();

        Venta venta = new Venta();
        venta.setTotal(carro.getTotal());
        venta.setPago(carro.getPago());

        // Obtener los productos por sus codigos
        for (ProductoDTO objeto : productosDTO) {
            Producto producto = productoRepository.findByBarcode(objeto.getBarcode());
            productos.add(producto);
        }

        // Crear las relaciones en la tabla intermedia VentaProducto
        Set<VentaProducto> ventasProductos = new HashSet<>();

        //para poder recorrer con indice
        int aux = 0;
        Producto[] array = productos.stream().toArray(Producto[]::new);
        ProductoDTO[] array2 = productosDTO.stream().toArray(ProductoDTO[]::new);

        while (aux < productosDTO.size()) {
            //modificar stock
            int nuevoStock = array[aux].getStock() - array2[aux].getCantidad();
            productoRepository.actualizarStockProducto(nuevoStock,array[aux].getBarcode());

            VentaProducto ventaProducto = new VentaProducto();
            ventaProducto.setVenta(venta);
            ventaProducto.setProducto(array[aux]);

            //ingresar la cantidad del producto desde el dto
            ventaProducto.setCantidad(array2[aux].getCantidad());
            ventasProductos.add(ventaProducto);
            aux++;
        }

        // Asociar las relaciones intermedias a la venta
        venta.setVentasProductos(ventasProductos);
        ventaRepository.save(venta);
    }

    @Override
    public List<Venta> ventasEntreFecha(String fechaInicio, String fechaFin) {

        //cambiar formato de fecha desde el front para que coincida con el formato de la bd
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSS");
        LocalDateTime desde = LocalDateTime.parse(fechaInicio, formatter);
        LocalDateTime hasta = LocalDateTime.parse(fechaFin, formatter);

        return ventaRepository.buscarVentasEntreFechas(desde, hasta);
    }

    @Override
    public List<Venta> listarVentas(){return ventaRepository.findAll();}

    @Override
    public void eliminarVenta(Venta venta) {
        ventaRepository.delete(venta);
    }
}

