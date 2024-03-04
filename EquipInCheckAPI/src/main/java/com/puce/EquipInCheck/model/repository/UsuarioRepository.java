package com.puce.EquipInCheck.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puce.EquipInCheck.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Usuario findByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);
    boolean existsByCorreoElectronico(String correoElectronico);

}
