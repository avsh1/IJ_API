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
    private String nombreCliente;
    private LocalDate fechaPrestamo;
    private String encargado;


    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    public  static enum  Status{
        ENTREGADO, RETRASADO, PRESTADO
    }

}
