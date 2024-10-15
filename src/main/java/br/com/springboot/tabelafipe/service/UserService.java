package br.com.springboot.tabelafipe.service;

import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.entity.UserEntity;
import org.springframework.data.domain.Page;

public interface UserService {

    UserEntity findById(Long id);

    void saveUser(UserDTO userDTO);

    void updateUser(UserDTO userDTO);

    void deleteUser(Long id);

    UserEntity findUser(String cpf);

    Page<UserEntity> getUserListPaginated(int selectedPage, int pageSize, String globalStatus);
}
