package com.puce.EquipInCheck.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.puce.EquipInCheck.model.entity.Equipo;
import com.puce.EquipInCheck.model.services.EquipoService;

import java.util.List;

@RestController
@RequestMapping("/api/equipos")
@CrossOrigin(origins = { "*" })
public class EquipoController {

    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<Equipo>> getAllEquipos() {
        List<Equipo> equipos = equipoService.obtenerTodosEquipos();
        return ResponseEntity.ok(equipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> getEquipoById(@PathVariable Long id) {
        Equipo equipo = equipoService.obtenerEquipoPorId(id);
        if (equipo != null) {
            return ResponseEntity.ok(equipo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Equipo> saveEquipo(@RequestBody Equipo equipo) {
        Equipo savedEquipo = equipoService.guardarEquipo(equipo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEquipo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo equipo) {
        Equipo updatedEquipo = equipoService.actualizarEquipo(id, equipo);

        return ResponseEntity.ok(updatedEquipo);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquipo(@PathVariable Long id) {
        equipoService.eliminarEquipo(id);
        return ResponseEntity.ok().build();
    }
}

