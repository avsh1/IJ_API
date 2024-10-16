package org.esfe.dtos.prestamo;


import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.cliente.ClienteSalida;
import org.esfe.dtos.libro.LibroSalida;


import java.io.Serializable;
import java.time.LocalDate;


@Getter
@Setter
public class PrestamoSalida implements Serializable {
    private Integer id;
    private ClienteSalida cliente;
    private LibroSalida libro;
    private String status;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;
    private String encargado;
}
