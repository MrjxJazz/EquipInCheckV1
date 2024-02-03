package com.puce.EquipInCheck.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.puce.EquipInCheck.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
