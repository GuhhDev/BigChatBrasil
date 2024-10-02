package br.com.bigchatbrasil.repositories;

import br.com.bigchatbrasil.models.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
}
