package org.esfe.controladores;

import org.esfe.dtos.libro.LibroGuardar;
import org.esfe.dtos.libro.LibroModificar;
import org.esfe.dtos.libro.LibroSalida;
import org.esfe.servicios.interfaces.ILibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
public class LibroController {
    @Autowired
    private ILibroService libroService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<LibroSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<LibroSalida> libros = libroService.obtenerTodosPaginados(pageable);

        if(libros.hasContent()){
            return ResponseEntity.ok(libros);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<LibroSalida>> mostrarTodos(){
        List<LibroSalida> libros = libroService.obtenerTodos();

        if(!libros.isEmpty()){
            return ResponseEntity.ok(libros);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<LibroSalida> mostrarPorId(@PathVariable Integer id){
        LibroSalida libro = libroService.obtenerPorId(id);

        if(libro != null){
            return ResponseEntity.ok(libro);
        }
        return ResponseEntity.notFound().build();
    }

  @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<LibroSalida> crear(@RequestBody LibroGuardar libroGuardar){
        LibroSalida libro = libroService.crear(libroGuardar);

        if(libro != null){
            return ResponseEntity.ok(libro);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<LibroSalida> editar(@PathVariable Integer id, @RequestBody LibroModificar libroModificar){
        LibroSalida libro = libroService.editar(libroModificar);

        if(libro != null){
            return ResponseEntity.ok(libro);
        }

        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        libroService.eliminarPorId(id);
        return ResponseEntity.ok("Libro eliminado correctamente");
    }
}

