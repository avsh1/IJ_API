package org.esfe.servicios.inplementaciones;


import org.esfe.dtos.prestamo.PrestamoGuardar;
import org.esfe.dtos.prestamo.PrestamoModificar;
import org.esfe.dtos.prestamo.PrestamoSalida;
import org.esfe.modelos.Prestamo;
import org.esfe.repositorios.IPrestamoRepository;
import org.esfe.servicios.interfaces.IPrestamoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrestamoService implements IPrestamoService {

    @Autowired
    private IPrestamoRepository prestamoRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<PrestamoSalida> obtenerTodos() {
        List<Prestamo> prestamos = prestamoRepository.findAll();

        return prestamos.stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoSalida.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<PrestamoSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<Prestamo> page = prestamoRepository.findAll(pageable);

        List<PrestamoSalida> prestamoDto = page.stream()
                .map(prestamo -> modelMapper.map(prestamo, PrestamoSalida.class))
                .collect(Collectors.toList());

        return new PageImpl<>(prestamoDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public PrestamoSalida obtenerPorId(Integer id) {
        return modelMapper.map(prestamoRepository.findById(id).get(), PrestamoSalida.class);

    }

    @Override
    public PrestamoSalida crear(PrestamoGuardar prestamoGuardar) {
        Prestamo prestamo = prestamoRepository.save(modelMapper.map(prestamoGuardar, Prestamo.class));
        return modelMapper.map(prestamo, PrestamoSalida.class);
    }

    @Override
    public PrestamoSalida editar(PrestamoModificar prestamoModificar) {
        Prestamo prestamo = prestamoRepository.save(modelMapper.map(prestamoModificar, Prestamo.class));
        return modelMapper.map(prestamo, PrestamoSalida.class);
    }

    // @Override
    // public PrestamoSalida cambiarEstado(PrestamoCambiarEstado prestamoCambiarEstado) {
    //   return null;
    // }

    @Override
    public void eliminarPorId(Integer id) {
        prestamoRepository.deleteById(id);
    }
}
