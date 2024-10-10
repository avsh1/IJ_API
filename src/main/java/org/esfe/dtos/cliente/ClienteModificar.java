package org.esfe.dtos.cliente;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ClienteModificar implements Serializable {


    private Integer id;

    private String nombre;

    private String Apellido;

    private String Edad;

    private String Correo;

    private String Genero;

}
