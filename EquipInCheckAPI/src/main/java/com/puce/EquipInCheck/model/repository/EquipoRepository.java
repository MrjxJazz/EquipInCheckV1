package com.puce.EquipInCheck.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puce.EquipInCheck.model.entity.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {

}
