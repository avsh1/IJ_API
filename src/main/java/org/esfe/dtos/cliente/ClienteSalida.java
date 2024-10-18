package org.esfe.dtos.cliente;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter

public class ClienteSalida implements Serializable {

    private Integer id;
    private String direccion;
    private String correo;
    private String telefono;
    private LocalDate fechaRegistro;
    private boolean estado;

}
