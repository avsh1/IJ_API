package org.esfe.dtos.libro;

import lombok.Getter;
import lombok.Setter;
import org.esfe.dtos.autor.AutorSalida;
import org.esfe.dtos.categoria.CategoriaSalida;
import org.esfe.dtos.editorial.EditorialSalida;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class LibroSalida implements Serializable {
    private Integer id;

    private String titulo;

    private LocalDate publicacion;

    private EditorialSalida editorial;

    private AutorSalida autor;

    private CategoriaSalida categoria;
}
