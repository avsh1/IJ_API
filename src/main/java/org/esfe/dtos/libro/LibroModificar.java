package org.esfe.dtos.libro;

import lombok.Getter;
import lombok.Setter;


import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class LibroModificar implements Serializable {
    private Integer id;

    private String titulo;

    private String nombreAutor;

    private LocalDate publicacion;

    private Integer autor;

    private Integer editorial;

    private Integer categoria;


}
