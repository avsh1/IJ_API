package org.esfe.repositorios;

import org.esfe.modelos.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository <Cliente, Integer> {
}
