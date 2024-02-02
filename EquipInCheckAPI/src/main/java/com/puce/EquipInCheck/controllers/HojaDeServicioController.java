package com.puce.EquipInCheck.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.puce.EquipInCheck.model.entity.HojaDeServicio;
import com.puce.EquipInCheck.model.services.HojaDeServicioService;

import java.util.List;
@RestController
@RequestMapping("/api/hojas-de-servicio")
@CrossOrigin(origins = { "*" })
public class HojaDeServicioController {

    @Autowired
    private HojaDeServicioService hojaDeServicioService;

    @GetMapping
    public ResponseEntity<List<HojaDeServicio>> getAllHojasDeServicio() {
        List<HojaDeServicio> hojasDeServicio = hojaDeServicioService.obtenerTodasHojasDeServicio();
        return ResponseEntity.ok(hojasDeServicio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HojaDeServicio> getHojaDeServicioById(@PathVariable Long id) {
        HojaDeServicio hojaDeServicio = hojaDeServicioService.obtenerHojaDeServicioPorId(id);
        if (hojaDeServicio != null) {
            return ResponseEntity.ok(hojaDeServicio);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

   

    @PutMapping("/{id}")
    public ResponseEntity<HojaDeServicio> updateHojaDeServicio(@PathVariable Long id, @RequestBody HojaDeServicio hojaDeServicio) {
        HojaDeServicio updatedHojaDeServicio = hojaDeServicioService.actualizarHojaDeServicio(id, hojaDeServicio);
        return ResponseEntity.ok(updatedHojaDeServicio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHojaDeServicio(@PathVariable Long id) {
        hojaDeServicioService.eliminarHojaDeServicio(id);
        return ResponseEntity.ok().build();
    }
}
