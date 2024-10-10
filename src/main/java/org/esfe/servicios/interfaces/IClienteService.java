package org.esfe.servicios.interfaces;

import org.esfe.dtos.cliente.ClienteGuardar;
import org.esfe.dtos.cliente.ClienteModificar;
import org.esfe.dtos.cliente.ClienteSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClienteService {

    List<ClienteSalida> obtenerTodos();

    Page<ClienteSalida> obtenerTodosPaginados(Pageable pageable);

    ClienteSalida obtenerPorId(Integer id);

    ClienteSalida crear(ClienteGuardar clienteGuardar);

    ClienteSalida editar(ClienteModificar clienteModificar);

    void eliminarPorId(Integer id);
}
