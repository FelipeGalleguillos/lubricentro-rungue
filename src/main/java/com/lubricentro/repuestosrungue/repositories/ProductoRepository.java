package com.lubricentro.repuestosrungue.repositories;

import com.lubricentro.repuestosrungue.classes.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    public Producto findByBarcode(String barcode);

    @Query("UPDATE Producto p SET p.stock = :nuevoStock WHERE p.barcode = :barcode")
    void actualizarStockProducto(@Param("nuevoStock") int nuevoStock, @Param("barcode") String barcode);
}
