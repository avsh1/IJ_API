package org.esfe.repositorios;

import org.esfe.modelos.DetallePrestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDetallePrestamoRepository  extends JpaRepository<DetallePrestamo, Integer> {
}
