package org.esfe.modelos;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombrelibro;
    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;


    @Enumerated(EnumType.STRING)
    private Status estado;

    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public  static enum  Status{
        ENTREGADO, RETRASADO, PRESTADO
    }

}
