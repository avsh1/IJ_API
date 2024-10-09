package org.esfe.controladores;


import org.esfe.dtos.editorial.EditorialGuardar;
import org.esfe.dtos.editorial.EditorialModificar;
import org.esfe.dtos.editorial.EditorialSalida;
import org.esfe.servicios.interfaces.IEditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/editoriales")
public class EditorialController {

    @Autowired
    private IEditorialService editorialService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<EditorialSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<EditorialSalida> editoriales = editorialService.obtenerTodosPaginados(pageable);
        if(editoriales.hasContent()){
            return ResponseEntity.ok(editoriales);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<EditorialSalida>> mostrarTodos(){
        List<EditorialSalida> editoriales = editorialService.obtenerTodos();
        if(!editoriales.isEmpty()){
            return ResponseEntity.ok(editoriales);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<EditorialSalida> buscarPorId(@PathVariable Integer id){
        EditorialSalida editorial  = editorialService.obtenerPorId(id);

        if(editorial != null){
            return ResponseEntity.ok(editorial);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EditorialSalida> crear(@RequestBody EditorialGuardar editorialGuardar){
        EditorialSalida editorial  = editorialService.crear(editorialGuardar);
        return ResponseEntity.ok(editorial);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<EditorialSalida> editar(@PathVariable Integer id, @RequestBody EditorialModificar editorialModificar){
        EditorialSalida editorial = editorialService.editar(editorialModificar);
        return ResponseEntity.ok(editorial);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        editorialService.eliminarPorId(id);
        return ResponseEntity.ok("Editorial eliminada correctamente");
    }
}
