package com.puce.EquipInCheck.model.services;

import java.util.List;
import com.puce.EquipInCheck.model.entity.HojaDeServicio;

public interface HojaDeServicioService {

    List<HojaDeServicio> obtenerTodasHojasDeServicio();
    HojaDeServicio obtenerHojaDeServicioPorId(Long id);
    HojaDeServicio actualizarHojaDeServicio(Long id, HojaDeServicio hojaDeServicio);
    void eliminarHojaDeServicio(Long id);
    HojaDeServicio guardarHojaDeServicio(HojaDeServicio hojaDeServicio);


}
