package org.esfe.servicios.inplementaciones;

import org.esfe.dtos.cliente.ClienteGuardar;
import org.esfe.dtos.cliente.ClienteModificar;
import org.esfe.dtos.cliente.ClienteSalida;
import org.esfe.modelos.Cliente;
import org.esfe.repositorios.IClienteRepository;
import org.esfe.servicios.interfaces.IClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClienteSalida> obtenerTodos() {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteSalida.class))
                .collect(Collectors.toList());

    }

    @Override
    public Page<ClienteSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Cliente> page = clienteRepository.findAll(pageable);

        List<ClienteSalida> clienteDto = page.stream()
                .map(cliente -> modelMapper.map(cliente, ClienteSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(clienteDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public ClienteSalida obtenerPorId(Integer id) {
        return modelMapper.map(clienteRepository.findById(id).get(), ClienteSalida.class);
    }

    @Override
    public ClienteSalida crear(ClienteGuardar clienteGuardar) {
        Cliente cliente = clienteRepository.save(modelMapper.map(clienteGuardar, Cliente.class));
        return modelMapper.map(cliente, ClienteSalida.class);
    }

    @Override
    public ClienteSalida editar(ClienteModificar clienteModificar) {
        Cliente cliente = clienteRepository.save(modelMapper.map(clienteModificar, Cliente.class));
        return modelMapper.map(cliente, ClienteSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        clienteRepository.deleteById(id);

    }
}


