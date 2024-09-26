package br.com.springboot.tabelafipe.repository;

import br.com.springboot.tabelafipe.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "select u from Usuario u " +
            "where u.cpf =:cpf")
    Usuario buscarUsuario(String cpf);

}
