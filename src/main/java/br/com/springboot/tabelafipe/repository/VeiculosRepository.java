package br.com.springboot.tabelafipe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.springboot.tabelafipe.model.Veiculos;
import org.springframework.data.repository.query.Param;

public interface VeiculosRepository extends JpaRepository<Veiculos, Long> {
	
//@Query(value = "select * from Veiculos")
//List<Veiculos> buscarPorVeiculo(String name);

@Query(value = "select v from Veiculos v " +
        "where v.renavam =:renavam")
Veiculos buscarPorRenavan(@Param("renavam") String renavam);

//@Query(value = "SELECT COUNT(v) FROM Veiculo v WHERE upper(trim(v.renavam)) like %?1%")
//List<Veiculos> validaDuplicados(String renavam);
	
}
