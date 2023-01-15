package br.com.springboot.tabelafipe.repository;

import java.util.List;

import br.com.springboot.tabelafipe.status.Status;
import org.hibernate.LockOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import br.com.springboot.tabelafipe.model.Veiculos;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculos, Long> {
	
//@Query(value = "select * from Veiculos")
//List<Veiculos> buscarPorVeiculo(String name);

@Query(value = "select v from Veiculos v " +
        "where v.renavam =:renavam")
Veiculos buscarPorRenavan(@Param("renavam") String renavam);


@QueryHints({
        @QueryHint(
                name = "javax.persistence.lock.timeout",value = "-2")
})
@Lock(LockModeType.PESSIMISTIC_WRITE)
List<Veiculos> findAllByStatus(Status status);

//@Query(value = "SELECT COUNT(v) FROM Veiculo v WHERE upper(trim(v.renavam)) like %?1%")
//List<Veiculos> validaDuplicados(String renavam);
	
}
