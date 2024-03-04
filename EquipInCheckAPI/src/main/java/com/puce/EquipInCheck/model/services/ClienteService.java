package com.puce.EquipInCheck.model.services;

import java.util.List;
import com.puce.EquipInCheck.model.entity.Cliente;

public interface ClienteService {

    List<Cliente> obtenerTodosClientes();
    Cliente obtenerClientePorId(Long id);
    Cliente guardarCliente(Cliente cliente);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
    Cliente buscarClientePorCedula(String ruc);

   
}
