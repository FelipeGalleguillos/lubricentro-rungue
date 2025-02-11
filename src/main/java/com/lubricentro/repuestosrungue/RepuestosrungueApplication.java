package com.lubricentro.repuestosrungue;

import com.lubricentro.repuestosrungue.classes.Producto;
import com.lubricentro.repuestosrungue.classes.Usuario;
import com.lubricentro.repuestosrungue.classes.Venta;
import com.lubricentro.repuestosrungue.classes.VentaProducto;
import com.lubricentro.repuestosrungue.repositories.ProductoRepository;
import com.lubricentro.repuestosrungue.repositories.UsuarioRepository;
import com.lubricentro.repuestosrungue.repositories.VentaProductoRepository;
import com.lubricentro.repuestosrungue.repositories.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class RepuestosrungueApplication {

	public static void main(String[] args) {
		SpringApplication.run(RepuestosrungueApplication.class, args);
	}

	@Autowired
	PasswordEncoder passwordEncoder;

	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository, ProductoRepository productoRepository, VentaRepository ventaRepository, VentaProductoRepository ventaProductoRepository) {
		return args -> {

			Usuario usuario = new Usuario("admin1",passwordEncoder.encode("1234"));
			VentaProducto ventp1 = new VentaProducto();
			VentaProducto ventp2 = new VentaProducto();
			VentaProducto ventp3 = new VentaProducto();

			Venta venta1 = new Venta();
			Venta venta2 = new Venta();

			Producto producto1 = new Producto("123456","liquido freno","lubricantes",5,1000);
			Producto producto2 = new Producto("111111","aceite motor","aceites",6,3500);
			Producto producto3 = new Producto("222222","aceite motor","aceites",1,4000);

			productoRepository.save(producto1);
			productoRepository.save(producto2);
			productoRepository.save(producto3);

			//primera venta
			ventp1.setProducto(producto1);
			ventp1.setVenta(venta1);
			Set<VentaProducto> ventasps = new HashSet<>();
			ventasps.add(ventp1);

			venta1.setPago("tarjeta");
			venta1.setTotal(5000);
			venta1.setFecha(LocalDateTime.now());
			venta1.setVentasProductos(ventasps);

			ventaRepository.save(venta1);

			//segunda venta

			ventp2.setProducto(producto2);
			ventp2.setVenta(venta2);
			ventp3.setProducto(producto3);
			ventp3.setVenta(venta2);

			Set<VentaProducto> ventas2s = new HashSet<>();
			ventas2s.add(ventp2);
			ventas2s.add(ventp3);

			venta2.setFecha(LocalDateTime.now().plusMonths(1));
			venta2.setTotal(6000);
			venta2.setPago("efectivo");
			venta2.setVentasProductos(ventas2s);

			ventaRepository.save(venta2);
			usuarioRepository.save(usuario);

		};
	}

}
