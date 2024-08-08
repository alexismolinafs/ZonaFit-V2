package gm.zona_fit.repository;

import gm.zona_fit.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteDao extends JpaRepository<Cliente, Integer> {

}
