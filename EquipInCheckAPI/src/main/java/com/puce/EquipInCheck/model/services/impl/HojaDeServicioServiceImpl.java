package com.puce.EquipInCheck.model.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.puce.EquipInCheck.model.entity.Cliente;
import com.puce.EquipInCheck.model.entity.Equipo;
import com.puce.EquipInCheck.model.entity.HojaDeServicio;
import com.puce.EquipInCheck.model.repository.ClienteRepository;
import com.puce.EquipInCheck.model.repository.EquipoRepository;
import com.puce.EquipInCheck.model.repository.HojaDeServicioRepository;
import com.puce.EquipInCheck.model.services.HojaDeServicioService;


@Service
public class HojaDeServicioServiceImpl implements HojaDeServicioService {

    @Autowired
    private HojaDeServicioRepository hojaDeServicioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public List<HojaDeServicio> obtenerTodasHojasDeServicio() {
        return hojaDeServicioRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public HojaDeServicio obtenerHojaDeServicioPorId(Long id) {
        return hojaDeServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hoja de servicio no encontrada"));
    }

   

    @Override
    public HojaDeServicio actualizarHojaDeServicio(Long id, HojaDeServicio hojaDeServicio) {
        HojaDeServicio hojaExistente = hojaDeServicioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Hoja de servicio no encontrada"));
    
  
        if (hojaDeServicio.getCliente() != null && hojaDeServicio.getCliente().getId() != null) {
            Cliente clienteExistente = clienteRepository.findById(hojaDeServicio.getCliente().getId())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        
            // Verificar si hay cambios antes de realizar la actualización
            if (!clienteExistente.equals(hojaDeServicio.getCliente())) {
                // Actualizar solo los campos que han cambiado
                clienteExistente.setEmpresa(hojaDeServicio.getCliente().getEmpresa());
                clienteExistente.setCargo(hojaDeServicio.getCliente().getCargo());
                clienteExistente.setNombre(hojaDeServicio.getCliente().getNombre());
                clienteExistente.setApellido(hojaDeServicio.getCliente().getApellido());
                clienteExistente.setTelefono(hojaDeServicio.getCliente().getTelefono());
                clienteExistente.setCorreo(hojaDeServicio.getCliente().getCorreo());
                clienteExistente.setDireccion(hojaDeServicio.getCliente().getDireccion());
                clienteExistente.setRuc(hojaDeServicio.getCliente().getRuc());
        
                clienteRepository.save(clienteExistente);
            }
        }
        
        
        // Actualizar Equipo solo si hay cambios
        if (hojaDeServicio.getEquipo() != null && hojaDeServicio.getEquipo().getId() != null) {
            Equipo equipoExistente = equipoRepository.findById(hojaDeServicio.getEquipo().getId())
                    .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

            // Verificar si hay cambios antes de realizar la actualización
            if (!equipoExistente.equals(hojaDeServicio.getEquipo())) {
                // Actualizar solo los campos que han cambiado
                equipoExistente.setTipo(hojaDeServicio.getEquipo().getTipo());
                equipoExistente.setMarca(hojaDeServicio.getEquipo().getMarca());
                equipoExistente.setModelo(hojaDeServicio.getEquipo().getModelo());
                equipoExistente.setProcesador(hojaDeServicio.getEquipo().getProcesador());
                equipoExistente.setOptico(hojaDeServicio.getEquipo().getOptico());
                equipoExistente.setDisco(hojaDeServicio.getEquipo().getDisco());
                equipoExistente.setOtros(hojaDeServicio.getEquipo().getOtros());
                equipoExistente.setCargador(hojaDeServicio.getEquipo().getCargador());
                equipoExistente.setBandeja(hojaDeServicio.getEquipo().getBandeja());
                equipoExistente.setSerie(hojaDeServicio.getEquipo().getSerie());
                equipoExistente.setMemoria(hojaDeServicio.getEquipo().getMemoria());
                equipoExistente.setBateria(hojaDeServicio.getEquipo().getBateria());
                equipoExistente.setNombre(hojaDeServicio.getEquipo().getNombre());

                equipoRepository.save(equipoExistente);
            }
        }

        return hojaDeServicioRepository.save(hojaExistente);
    }
    

    @Override
    public void eliminarHojaDeServicio(Long id) {
        hojaDeServicioRepository.deleteById(id);
    }

}
