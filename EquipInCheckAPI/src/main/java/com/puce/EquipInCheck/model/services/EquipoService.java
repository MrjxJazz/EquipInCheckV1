package com.puce.EquipInCheck.model.services;

import java.util.List;
import com.puce.EquipInCheck.model.entity.Equipo;

public interface EquipoService {

    List<Equipo> obtenerTodosEquipos();
    Equipo obtenerEquipoPorId(Long id);
    Equipo guardarEquipo(Equipo equipo);
    Equipo actualizarEquipo(Long id, Equipo equipo);
    void eliminarEquipo(Long id);

}
