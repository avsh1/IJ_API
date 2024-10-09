package org.esfe.servicios.inplementaciones;


import org.esfe.dtos.editorial.EditorialGuardar;
import org.esfe.dtos.editorial.EditorialModificar;
import org.esfe.dtos.editorial.EditorialSalida;
import org.esfe.modelos.Editorial;
import org.esfe.repositorios.IEditorialRepository;
import org.esfe.servicios.interfaces.IEditorialService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditorialService   implements IEditorialService {

        @Autowired
        private IEditorialRepository editorialRepository;

        @Autowired
        private ModelMapper modelMapper;

        @Override
        public List<EditorialSalida> obtenerTodos() {
            List<Editorial> editoriales = editorialRepository.findAll();

            return editoriales.stream()
                    .map(editorial -> modelMapper.map(editorial, EditorialSalida.class))
                    .collect(Collectors.toList());

        }

        @Override
        public Page<EditorialSalida> obtenerTodosPaginados(Pageable pageable) {
            Page<Editorial> page = editorialRepository.findAll(pageable);

            List<EditorialSalida> editorialesDto = page.stream()
                    .map(editorial -> modelMapper.map(editorial, EditorialSalida.class))
                    .collect(Collectors.toList());

            return new PageImpl<>(editorialesDto, page.getPageable(), page.getTotalElements());
        }

        @Override
        public EditorialSalida obtenerPorId(Integer id) {
            return modelMapper.map(editorialRepository.findById(id).get(), EditorialSalida.class);
        }

        @Override
        public EditorialSalida crear(EditorialGuardar editorialGuardar) {
            Editorial editorial = editorialRepository.save(modelMapper.map(editorialGuardar, Editorial.class));
            return modelMapper.map(editorial, EditorialSalida.class);
        }

        @Override
        public EditorialSalida editar(EditorialModificar editorialModificar) {
            Editorial editorial = editorialRepository.save(modelMapper.map(editorialModificar, Editorial.class));
            return modelMapper.map(editorial, EditorialSalida.class);
        }

        @Override
        public void eliminarPorId(Integer id) {
            editorialRepository.deleteById(id);

        }
}
