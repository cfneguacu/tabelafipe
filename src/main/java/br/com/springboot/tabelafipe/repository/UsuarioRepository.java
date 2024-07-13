package br.com.springboot.tabelafipe.repository;

import br.com.springboot.tabelafipe.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
