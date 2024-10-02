package br.com.bigchatbrasil.repositories;

import br.com.bigchatbrasil.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
