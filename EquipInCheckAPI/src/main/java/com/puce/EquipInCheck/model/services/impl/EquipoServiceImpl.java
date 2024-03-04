package com.puce.EquipInCheck.model.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.puce.EquipInCheck.model.entity.Equipo;
import com.puce.EquipInCheck.model.repository.EquipoRepository;
import com.puce.EquipInCheck.model.services.EquipoService;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<Equipo> obtenerTodosEquipos() {
        return equipoRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Equipo obtenerEquipoPorId(Long id) {
        return equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
    }

   
    @Override
    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }


    @Override
    public Equipo actualizarEquipo(Long id, Equipo equipo) {
        Equipo equipoExistente = equipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));
        equipoExistente.setTipo(equipo.getTipo());
        equipoExistente.setMarca(equipo.getMarca());
        equipoExistente.setModelo(equipo.getModelo());
        equipoExistente.setProcesador(equipo.getProcesador());
        equipoExistente.setOptico(equipo.getOptico());
        equipoExistente.setDisco(equipo.getDisco());
        equipoExistente.setOtros(equipo.getOtros());
        equipoExistente.setCargador(equipo.getCargador());
        equipoExistente.setBandeja(equipo.getBandeja());
        equipoExistente.setSerie(equipo.getSerie());
        equipoExistente.setMemoria(equipo.getMemoria());
        equipoExistente.setBateria(equipo.getBateria());
        equipoExistente.setCondicionesFisicasEsteticasIngreso(equipo.getCondicionesFisicasEsteticasIngreso());
        equipoExistente.setNombre(equipo.getNombre());
        equipoExistente.setEstadoEquipo(equipo.getEstadoEquipo());
     
        return equipoRepository.save(equipoExistente);
    }

    @Override
    public void eliminarEquipo(Long id) {
        equipoRepository.deleteById(id);
    }

}
