package com.lubricentro.repuestosrungue.repositories;

import com.lubricentro.repuestosrungue.classes.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource
public interface VentaRepository extends JpaRepository<Venta, Long> {

    @Query(value = "SELECT * FROM venta WHERE venta.fecha BETWEEN :fromDate AND :toDate", nativeQuery = true)
    public List<Venta> buscarVentasEntreFechas(@Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
}
