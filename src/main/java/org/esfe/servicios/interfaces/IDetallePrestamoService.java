package org.esfe.servicios.interfaces;

import org.esfe.dtos.detalle.DetallePrestamoGuardar;
import org.esfe.dtos.detalle.DetallePrestamoModificar;
import org.esfe.dtos.detalle.DetallePrestamoSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface IDetallePrestamoService {
    List<DetallePrestamoSalida> obtenerTodos();

    Page<DetallePrestamoSalida> obtenerTodosPaginados(Pageable pageable);

    DetallePrestamoSalida obtenerPorId(Integer id);

    DetallePrestamoSalida crear(DetallePrestamoGuardar detallePrestamoGuardar);

    DetallePrestamoSalida editar(DetallePrestamoModificar detallePrestamoModificar);

    void eliminarPorId(Integer id);
}
