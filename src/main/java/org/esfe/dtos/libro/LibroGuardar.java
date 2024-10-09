package org.esfe.dtos.libro;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter

public class LibroGuardar implements Serializable  {
    private String titulo;

    private LocalDate publicacion;

    private Integer autor_Id;

    private Integer editorialId;

    private Integer categoria_Id;


}
