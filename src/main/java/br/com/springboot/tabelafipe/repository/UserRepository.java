package br.com.springboot.tabelafipe.repository;

import br.com.springboot.tabelafipe.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query(value = "select u from UserEntity u " +
            "where u.cpf =:cpf")
    UserEntity findByCpf(@Param("cpf") String cpf);

}
