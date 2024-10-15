package br.com.springboot.tabelafipe.adapter;

import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Component
public class UserEntityAdapter {

    public UserEntity toModel(UserDTO userDTO) {

        return UserEntity.builder()
                .name(userDTO.getName())
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .vehicles()
                .build();
    }

}