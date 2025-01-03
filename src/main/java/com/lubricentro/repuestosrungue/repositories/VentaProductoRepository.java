package com.lubricentro.repuestosrungue.repositories;

import com.lubricentro.repuestosrungue.classes.VentaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface VentaProductoRepository extends JpaRepository <VentaProducto, Long> {

}
