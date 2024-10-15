package br.com.springboot.tabelafipe.repository;

import java.util.Arrays;
import java.util.List;

import br.com.springboot.tabelafipe.dto.VehicleDTO;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.persistence.QueryHint;

@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
	
//@Query(value = "select * from Veiculo")
//List<Veiculo> buscarPorVeiculo(String name);

@Query(value = "select v from Veiculo v " +
        "where v.renavam =:renavam")
VehicleEntity findByRenavan(@Param("renavam") String renavam);

@QueryHints({
        @QueryHint(
                name = "javax.persistence.lock.timeout",value = "true")
})
@Lock(LockModeType.PESSIMISTIC_WRITE)
List<VehicleEntity> findAllByStatus(Status status);

List<VehicleEntity> findAllByStatusOrderByDateDesc(Status status);

List<VehicleEntity> findAllByOrderByDateDesc();

//@Query(value = "SELECT COUNT(v) FROM Veiculo v WHERE upper(trim(v.renavam)) like %?1%")
//List<Veiculo> validaDuplicados(String renavam);
	
}
