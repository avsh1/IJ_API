package org.esfe.dtos.detalle;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class DetallePrestamoGuardar  implements Serializable {

    private Integer prestamoId;
    private Integer libro_Id;
    private LocalDate fechaDevolucion;
}
