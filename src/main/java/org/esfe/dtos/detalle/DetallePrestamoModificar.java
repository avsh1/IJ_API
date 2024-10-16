package org.esfe.dtos.detalle;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class DetallePrestamoModificar  implements Serializable {
    private Integer id;

    private Integer prestamo;

    private Integer libro;

    private LocalDate  fechaDevolucion;
}
