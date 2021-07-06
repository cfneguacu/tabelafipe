package br.com.springboot.tabelafipe.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.springboot.tabelafipe.model.Cadastro;

@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {
	
	@Query(value = "select c from Cadastro c where upper(trim(c.nome)) like %?1%")
	List<Cadastro> buscarPorNome(String name);
	
	@Query(value = "SELECT COUNT(c) FROM Cadastro c WHERE upper(trim(c.nome)) like %?1%")
	List<Cadastro> validaDuplicados(String name);

}
