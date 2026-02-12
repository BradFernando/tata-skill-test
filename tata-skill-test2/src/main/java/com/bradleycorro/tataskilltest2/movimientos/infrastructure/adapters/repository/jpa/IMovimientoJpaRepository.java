package com.bradleycorro.tataskilltest2.movimientos.infrastructure.adapters.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovimientoJpaRepository extends JpaRepository<MovimientoEntity, Long> {
    @Query("SELECT m FROM MovimientoEntity m WHERE m.cuenta.clienteId = :clienteId AND m.fecha BETWEEN :startDate AND :endDate")
    List<MovimientoEntity> findByClienteAndFechaRange(@Param("clienteId") Long clienteId, 
                                                      @Param("startDate") LocalDateTime startDate, 
                                                      @Param("endDate") LocalDateTime endDate);
    
    List<MovimientoEntity> findByCuentaIdOrderByFechaDesc(Long cuentaId);
}
