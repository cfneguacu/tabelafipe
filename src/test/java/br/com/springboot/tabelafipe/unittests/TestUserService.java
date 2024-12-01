package br.com.springboot.tabelafipe.unittests;

import br.com.springboot.tabelafipe.adapter.UserDTOAdapter;
import br.com.springboot.tabelafipe.adapter.UserEntityAdapter;
import br.com.springboot.tabelafipe.convert.StatusConvert;
import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.repository.UserRepository;
import br.com.springboot.tabelafipe.service.UserService;
import br.com.springboot.tabelafipe.service.impl.UserServiceImpl;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class TestUserService {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDTOAdapter userDTOAdapter;

    @Mock
    private UserEntityAdapter userEntityAdapter;

    @InjectMocks
    private UserServiceImpl userService;

    public TestUserService() {

        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        final UserDTO userDTO = new UserDTO();
        final UserEntity userEntity = new UserEntity();

        when(userEntityAdapter.toModel(userDTO)).thenReturn(userEntity);
        userService.saveUser(userDTO);

        verify(userEntityAdapter, times(1)).toModel(userDTO);
        verify(userRepository, times(1)).save(userEntity);

    }

    @Test
    public void testGetUserById() {
        Long id = 1L;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        UserDTO userDTO = new UserDTO();
        when(userDTOAdapter.toDTO(userEntity)).thenReturn(userDTO);
        UserDTO userDTOResult = userService.getUserById(id);

        assertNotNull(userDTOResult);
        assertEquals(userDTO, userDTOResult);

        verify(userRepository, times(1)).findById(id);
        verify(userDTOAdapter, times(1)).toDTO(userEntity);

    }

    @Test
    public void testDeleteUser() {
        Long id = 1L;
        userService.deleteUser(id);
        verify(userRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        userDTO.setName("Cláudio");
        Instant date = Instant.now();
        userDTO.setCpf("29328172802");
        userDTO.setEmail("cfneguacu@hotmail.com");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        when(userRepository.findById(userDTO.getId())).thenReturn(Optional.of(userEntity));

        userService.updateUser(userDTO);

        verify(userRepository, times(1)).findById(userDTO.getId());
        verify(userRepository, times(1)).save(userEntity);

        assertEquals("Cláudio", userEntity.getName());
        assertEquals("29328172802", userEntity.getCpf());
        assertEquals("cfneguacu@hotmail.com", userEntity.getEmail());


    }

    @Test
    public void testGetUserList() {
        List<UserEntity> userEntityList = Arrays.asList(new UserEntity(), new UserEntity(), new UserEntity());
        when(userRepository.findAll()).thenReturn(userEntityList);

        UserDTO userDTO = new UserDTO();
        UserEntity userEntity = new UserEntity();

        when(userDTOAdapter.toDTO(userEntity)).thenReturn(userDTO);

        List<UserDTO> userDTOList = userService.getUserList();

        assertNotNull(userDTOList);
        assertEquals(3, userDTOList.size());
        verify(userRepository, times(1)).findAll();
        verify(userDTOAdapter, times(3)).toDTO((UserEntity) any());
    }
}