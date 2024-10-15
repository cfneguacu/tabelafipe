package br.com.springboot.tabelafipe.service.impl;

import br.com.springboot.tabelafipe.adapter.UserDTOAdapter;
import br.com.springboot.tabelafipe.adapter.UserEntityAdapter;
import br.com.springboot.tabelafipe.adapter.VehicleDTOAdapter;
import br.com.springboot.tabelafipe.adapter.VehicleEntityAdapter;
import br.com.springboot.tabelafipe.convert.StatusConvert;
import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.exceptions.UserNotFoundException;
import br.com.springboot.tabelafipe.repository.UserRepository;
import br.com.springboot.tabelafipe.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final StatusConvert statusConvert;
    private final UserDTOAdapter userDTOAdapter;
    private final UserEntityAdapter userEntityAdapter;

    public UserServiceImpl(final UserRepository userRepository, final StatusConvert statusConvert, final UserDTOAdapter userDtoAdapter, final UserEntityAdapter userEntityAdapter){
        this.userRepository = userRepository;
        this.statusConvert = statusConvert;
        this.userDTOAdapter = userDTOAdapter;
        this.userEntityAdapter = userEntityAdapter;
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        try {
            final UserEntity userEntity = userEntityAdapter.toModel(userDTO);
            userRepository.save(userEntity);
        } catch(RuntimeException re){
            throw re;
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {

        VehicleEntityAdapter vehicleEntityAdapter = new VehicleEntityAdapter();

        try {
            final UserEntity optionalUserEntity = userRepository.findByCpf(userDTO.getCpf());
            if (optionalUserEntity != null) {
                optionalUserEntity.setName(userDTO.getName());
                optionalUserEntity.setCpf(userDTO.getCpf());
                optionalUserEntity.setEmail(userDTO.getEmail());
                List<VehicleEntity> vehicleEntityList = userDTO.getVehicles()
                        .stream()
                        .map(vehicleEntityAdapter::toModel)
                        .collect(Collectors.toList());
                optionalUserEntity.setVehicles(vehicleEntityList);
                userRepository.save(optionalUserEntity);
            } else {
                throw new UserNotFoundException("Task with id " + userDTO.getId() + " not found");
            }
        }catch(final RuntimeException re) {
            throw re;
        }
    }

    @Override
    public void deleteUser(Long id) {
            userRepository.deleteById(id);
    }

    @Override
    public UserEntity findUser(String cpf){
        return userRepository.findByCpf(cpf);
    }

    @Override
    public Page<UserEntity> getUserListPaginated(int selectedPage, int pageSize, String globalStatus) {
        return null;
    }
}
