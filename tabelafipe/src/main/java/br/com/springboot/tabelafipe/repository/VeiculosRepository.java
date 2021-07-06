package br.com.springboot.tabelafipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.springboot.tabelafipe.model.Veiculos;

public interface VeiculosRepository extends JpaRepository<Veiculos, Long> {
	
//@Query(value = "select * from Veiculos")
//List<Veiculos> buscarPorVeiculo(String name);

//@Query(value = "select v from Veiculo v where upper(trim(v.marca)) like %?1%")
//List<Veiculos> buscarPorNome(String name);

//@Query(value = "SELECT COUNT(v) FROM Veiculo v WHERE upper(trim(v.marca)) like %?1%")
//List<Veiculos> validaDuplicados(String name);
	
}
