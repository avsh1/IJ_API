package org.esfe.dtos.editorial;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter

public class EditorialModificar  implements Serializable {

    private Integer id;
    private String nombre;
    private String pais;
    private String email;
    private String telefono;

}
