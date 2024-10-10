package org.esfe.controladores;

import org.esfe.dtos.cliente.ClienteModificar;
import org.esfe.dtos.cliente.ClienteGuardar;
import org.esfe.dtos.cliente.ClienteSalida;
import org.esfe.servicios.interfaces.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<Page<ClienteSalida>> mostrarTodosPaginados(Pageable pageable){
        Page<ClienteSalida> cliente = clienteService.obtenerTodosPaginados(pageable);
        if(cliente.hasContent()){
            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/lista")
    public ResponseEntity<List<ClienteSalida>> mostrarTodos(){
        List<ClienteSalida> cliente = clienteService.obtenerTodos();
        if(!cliente.isEmpty()){
            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteSalida> buscarPorId(@PathVariable Integer id){
        ClienteSalida cliente = clienteService.obtenerPorId(id);

        if(cliente != null){
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.notFound().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ClienteSalida> crear(@RequestBody ClienteGuardar clienteGuardar){
        ClienteSalida cliente = clienteService.crear(clienteGuardar);
        return ResponseEntity.ok(cliente);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteSalida> editar(@PathVariable Integer id, @RequestBody ClienteModificar clienteModificar){
        ClienteSalida cliente = clienteService.editar(clienteModificar);
        return ResponseEntity.ok(cliente);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Integer id){
        clienteService.eliminarPorId(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }

}
