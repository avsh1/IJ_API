package org.esfe.dtos.detalle;


import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.libro.LibroSalida;
import org.esfe.dtos.prestamo.PrestamoSalida;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class DetallePrestamoSalida  implements Serializable {

    private Integer id;
    private PrestamoSalida prestamo;
    private LibroSalida libro;
    private LocalDate fechaDevolucion;
}
