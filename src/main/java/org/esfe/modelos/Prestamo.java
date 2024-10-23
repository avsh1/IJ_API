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

    // Relación con la entidad Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;


    private LocalDate fechaPrestamo;
    private LocalDate fechaDevolucion;

    private String encargado;

    // Relación con la entidad Libro
    @ManyToOne
    @JoinColumn(name = "libro_id")
    private Libro libro;

    // Enum para representar el estado del préstamo
    @Enumerated(EnumType.STRING)
    private Status status;

    // Enumeración para los estados del préstamo
    public static enum Status {
        ENTREGADO, RETRASADO, PRESTADO
    }

    @OneToMany(mappedBy = "prestamo")
    private List<DetallePrestamo> detalles;

}
