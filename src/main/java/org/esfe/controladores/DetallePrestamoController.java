package org.esfe.controladores;
import org.esfe.dtos.detalle.DetallePrestamoGuardar;
import org.esfe.dtos.detalle.DetallePrestamoModificar;
import org.esfe.dtos.detalle.DetallePrestamoSalida;
import org.esfe.servicios.interfaces.IDetallePrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalle-prestamos")
public class DetallePrestamoController {

    @Autowired
    private IDetallePrestamoService detallePrestamoService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<DetallePrestamoSalida>> mostrarTodosPaginados(Pageable pageable) {
        Page<DetallePrestamoSalida> detalles = detallePrestamoService.obtenerTodosPaginados(pageable);

        if (detalles.hasContent()) {
            return ResponseEntity.ok(detalles);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<DetallePrestamoSalida>> mostrarTodos() {
        List<DetallePrestamoSalida> detalles = detallePrestamoService.obtenerTodos();

        if (!detalles.isEmpty()) {
            return ResponseEntity.ok(detalles);
        }
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<DetallePrestamoSalida> mostrarPorId(@PathVariable Integer id) {
        DetallePrestamoSalida detalle = detallePrestamoService.obtenerPorId(id);

        if (detalle != null) {
            return ResponseEntity.ok(detalle);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<DetallePrestamoSalida> crear(@RequestBody DetallePrestamoGuardar detallePrestamoGuardar) {
        DetallePrestamoSalida detalle = detallePrestamoService.crear(detallePrestamoGuardar);

        if (detalle != null) {
            return ResponseEntity.ok(detalle);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<DetallePrestamoSalida> editar(@PathVariable Integer id, @RequestBody DetallePrestamoModificar detallePrestamoModificar) {
        DetallePrestamoSalida detalle = detallePrestamoService.editar( detallePrestamoModificar);

        if (detalle != null) {
            return ResponseEntity.ok(detalle);
        }
        return ResponseEntity.internalServerError().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id) {
        detallePrestamoService.eliminarPorId(id);
        return ResponseEntity.ok("Detalle de préstamo eliminado correctamente");
    }

}
