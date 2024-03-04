package com.puce.EquipInCheck.model.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.puce.EquipInCheck.model.entity.Usuario;
import com.puce.EquipInCheck.model.repository.UsuarioRepository;
import com.puce.EquipInCheck.model.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> usuarios() {
        return usuarioRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Usuario buscarUsuarioId(Long id) {
        return usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void cambioEstado(Long id, boolean estado) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setEstado(estado);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario validarInicioSesion(String correo_electronico, String contrasena) {
        return usuarioRepository.findByCorreoElectronicoAndContrasena(correo_electronico, contrasena);
    }

    @Override
    public boolean existeCorreoElectronico(String correoElectronico) {
        return usuarioRepository.existsByCorreoElectronico(correoElectronico);
    }

    

    @Override
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}
