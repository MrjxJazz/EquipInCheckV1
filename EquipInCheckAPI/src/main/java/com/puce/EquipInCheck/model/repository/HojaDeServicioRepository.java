package com.puce.EquipInCheck.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.puce.EquipInCheck.model.entity.HojaDeServicio;

public interface HojaDeServicioRepository extends JpaRepository<HojaDeServicio, Long> {

    @Query("SELECT MAX(h.id) FROM HojaDeServicio h")
    Long findMaxId();
}
