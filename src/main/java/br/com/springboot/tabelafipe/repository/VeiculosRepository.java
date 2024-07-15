package br.com.springboot.tabelafipe.repository;

import java.util.List;

import br.com.springboot.tabelafipe.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import br.com.springboot.tabelafipe.model.Veiculo;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface VeiculosRepository extends JpaRepository<Veiculo, Long> {
	
//@Query(value = "select * from Veiculo")
//List<Veiculo> buscarPorVeiculo(String name);

@Query(value = "select v from Veiculo v " +
        "where v.renavam =:renavam")
Veiculo buscarPorRenavan(@Param("renavam") String renavam);

@Query(value = "select v from Veiculo v " +
            "where v.usuario_id =:id")
Veiculo buscarPorId(@Param("id") Long id);


@QueryHints({
        @QueryHint(
                name = "javax.persistence.lock.timeout",value = "true")
})
@Lock(LockModeType.PESSIMISTIC_WRITE)
List<Veiculo> findAllByStatus(Status status);

//@Query(value = "SELECT COUNT(v) FROM Veiculo v WHERE upper(trim(v.renavam)) like %?1%")
//List<Veiculo> validaDuplicados(String renavam);
	
}
