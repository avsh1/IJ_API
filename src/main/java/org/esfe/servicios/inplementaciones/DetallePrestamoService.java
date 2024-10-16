package org.esfe.servicios.inplementaciones;

import org.esfe.dtos.detalle.DetallePrestamoGuardar;
import org.esfe.dtos.detalle.DetallePrestamoModificar;
import org.esfe.dtos.detalle.DetallePrestamoSalida;
import org.esfe.modelos.DetallePrestamo;
import org.esfe.repositorios.IDetallePrestamoRepository;
import org.esfe.servicios.interfaces.IDetallePrestamoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DetallePrestamoService  implements IDetallePrestamoService {

    @Autowired
    private IDetallePrestamoRepository detallePrestamoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DetallePrestamoSalida> obtenerTodos() {
        List<DetallePrestamo> detallesprestamos = detallePrestamoRepository.findAll();

        return detallesprestamos.stream()
                .map(detallePrestamo -> modelMapper.map(detallePrestamo, DetallePrestamoSalida.class))
                .collect(Collectors.toList());    }

    @Override
    public Page<DetallePrestamoSalida> obtenerTodosPaginados(Pageable pageable) {
        Page<DetallePrestamo> page = detallePrestamoRepository.findAll(pageable);

        List<DetallePrestamoSalida> detallesPrestamosDto = page.stream()
                .map(detallePrestamo -> modelMapper.map(detallePrestamo, DetallePrestamoSalida.class))
                .collect(Collectors.toList());
        return new PageImpl<>(detallesPrestamosDto, page.getPageable(), page.getTotalElements());
    }

    @Override
    public DetallePrestamoSalida obtenerPorId(Integer id) {
        Optional<DetallePrestamo> detallePrestamo = detallePrestamoRepository.findById(id);

        if(detallePrestamo.isPresent()){
            return modelMapper.map(detallePrestamo.get(), DetallePrestamoSalida.class);
        }
        return null;
    }

    @Override
    public DetallePrestamoSalida crear(DetallePrestamoGuardar detallePrestamoGuardar) {
        DetallePrestamo detallePrestamo = modelMapper.map(detallePrestamoGuardar, DetallePrestamo.class);
        detallePrestamo.setId(null);
        DetallePrestamo resp = detallePrestamoRepository.save(detallePrestamo);
        return modelMapper.map(resp, DetallePrestamoSalida.class);
    }

    @Override
    public DetallePrestamoSalida editar(DetallePrestamoModificar detallePrestamoModificar) {
        DetallePrestamo proyecto = detallePrestamoRepository.save(modelMapper.map(detallePrestamoModificar, DetallePrestamo.class));

        return modelMapper.map(proyecto, DetallePrestamoSalida.class);
    }

    @Override
    public void eliminarPorId(Integer id) {
        detallePrestamoRepository.deleteById(id);
    }
}
