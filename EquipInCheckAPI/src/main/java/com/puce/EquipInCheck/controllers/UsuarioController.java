package com.puce.EquipInCheck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.puce.EquipInCheck.Utils.UsuarioLogeado;
import com.puce.EquipInCheck.model.entity.Usuario;
import com.puce.EquipInCheck.model.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = { "*" })
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsuarios() {
        List<Usuario> usuarios = usuarioService.usuarios();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUsuarioId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario) {
        Usuario savedUsuario = usuarioService.guardarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> validLogeo(@RequestBody UsuarioLogeado usuariologeado) {

        Usuario usuario = usuarioService.validarInicioSesion(usuariologeado.getCorreo(),
                usuariologeado.getContrasena());

        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }

    @GetMapping("/existe-correo")
    public ResponseEntity<Boolean> existeCorreoElectronico(@RequestParam String correoElectronico) {
        boolean existe = usuarioService.existeCorreoElectronico(correoElectronico);
        return ResponseEntity.ok(existe);
    }

    @PostMapping("/cambiar-estado/{id}/{estado}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @PathVariable boolean estado) {
        try {
            usuarioService.cambioEstado(id, estado);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.ok().build();
    }

}
