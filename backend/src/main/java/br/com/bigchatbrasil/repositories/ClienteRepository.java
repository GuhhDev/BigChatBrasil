package br.com.bigchatbrasil.repositories;

import br.com.bigchatbrasil.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByTelefone(String telefone);
}
