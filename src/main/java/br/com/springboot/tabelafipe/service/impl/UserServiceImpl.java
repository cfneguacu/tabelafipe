package br.com.springboot.tabelafipe.service.impl;

import br.com.springboot.tabelafipe.adapter.*;
import br.com.springboot.tabelafipe.adapter.UserDTOAdapter;
import br.com.springboot.tabelafipe.adapter.UserEntityAdapter;
import br.com.springboot.tabelafipe.convert.StatusConvert;
import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.exceptions.UserNotFoundException;
import br.com.springboot.tabelafipe.repository.UserRepository;
import br.com.springboot.tabelafipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserDTOAdapter userDTOAdapter;

    private final UserEntityAdapter userEntityAdapter;

    private final VehicleEntityAdapter vehicleEntityAdapter;

    public UserServiceImpl(final UserRepository userRepository, final UserDTOAdapter userDTOAdapter, final UserEntityAdapter userEntityAdapter, final VehicleEntityAdapter vehicleEntityAdapter){
        this.userRepository = userRepository;
        this.vehicleEntityAdapter = vehicleEntityAdapter;
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
        
        try {
            final UserEntity optionalUserEntity = userRepository.findByCpf(userDTO.getCpf());
            if (optionalUserEntity != null) {
                optionalUserEntity.setName(userDTO.getName());
                optionalUserEntity.setCpf(userDTO.getCpf());
                optionalUserEntity.setEmail(userDTO.getEmail());
                List<VehicleEntity> vehicleEntityList = userDTO.getVehicleDTOList()
                        .stream()
                        .map(vehicleEntityAdapter::toModel)
                        .collect(Collectors.toList());
                optionalUserEntity.setVehicles(vehicleEntityList);
                userRepository.save(optionalUserEntity);
            } else {
                throw new UserNotFoundException("User with id " + userDTO.getId() + " not found");
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
    public UserDTO getUserByCpf(String cpf){
        UserEntity userEntity = userRepository.findByCpf(cpf);
        if(userEntity != null){
            return userDTOAdapter.toDTO(userEntity);
        }else{
            throw new UserNotFoundException("User with id "+ cpf +" not found");
        }
        
        
    }

    public List<UserDTO> getUserList(){
        return userRepository.findAll()
                .stream()
                .map(userDTOAdapter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserDTO> getUserListPaginated(int pageNo, int pageSize) {
        List<UserDTO> userDTOList;
        Page<UserDTO> page;
        userDTOList = getUserList();
        pageNo = 1;



        if(!userDTOList.isEmpty()){
            if(userDTOList.size() < pageSize){
                pageSize = userDTOList.size();
            }
            Pageable pageable = PageRequest.of(pageNo -1, pageSize);
            int start = (int)pageable.getOffset();
            int end = Math.min((start + pageable.getPageSize()), userDTOList.size());
            List<UserDTO> subList = userDTOList.subList(start, end);
            page = new PageImpl<>(subList, pageable, userDTOList.size());
        }else{
            page = Page.empty();
        }

        return page;
    }
}
