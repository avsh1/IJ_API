package org.esfe.modelos;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String direccion;
    private String correo;
    private String telefono;
    private LocalDate fechaRegistro;
    private boolean estado;

    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamos;
 }


