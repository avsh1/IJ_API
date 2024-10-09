package org.esfe.servicios.interfaces;


import org.esfe.dtos.editorial.EditorialGuardar;
import org.esfe.dtos.editorial.EditorialModificar;
import org.esfe.dtos.editorial.EditorialSalida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IEditorialService {

    List<EditorialSalida> obtenerTodos();

    Page<EditorialSalida> obtenerTodosPaginados(Pageable pageable);

    EditorialSalida obtenerPorId(Integer id);

    EditorialSalida crear(EditorialGuardar editorialGuardar);

    EditorialSalida editar(EditorialModificar editorialModificar);

    void eliminarPorId(Integer id);
}
