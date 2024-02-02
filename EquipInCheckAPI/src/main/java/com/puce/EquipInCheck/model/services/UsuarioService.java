package com.puce.EquipInCheck.model.services;

import java.util.List;


import com.puce.EquipInCheck.model.entity.Usuario;


public interface UsuarioService {

    List<Usuario> usuarios();
    Usuario buscarUsuarioId(Long id);
    Usuario guardarUsuario(Usuario usuario);
    void cambioEstado(Long id, boolean estado);
    Usuario validarInicioSesion(String correo_electronico, String contrasena);
    boolean existeCorreoElectronico(String correoElectronico);
    void eliminarUsuario(Long id);


}
