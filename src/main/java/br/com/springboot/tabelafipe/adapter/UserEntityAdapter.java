package br.com.springboot.tabelafipe.adapter;


import br.com.springboot.tabelafipe.convert.InstantConvert;
import br.com.springboot.tabelafipe.dto.BrandDTO;
import br.com.springboot.tabelafipe.dto.ModelDTO;
import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.dto.VehicleDTO;
import br.com.springboot.tabelafipe.entity.*;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityAdapter {

    private InstantConvert instantConvert;

    private VehicleEntityAdapter vehicleEntityAdapter;

    public UserEntity toModel(UserDTO userDTO) {


        List<VehicleEntity> vehicleEntityList = new ArrayList<>();
        List<VehicleDTO> vehicleDTOList = userDTO.getVehicles();

        vehicleEntityList = vehicleDTOList.stream()
                .map(vehicleEntityAdapter::toModel)
                .collect(Collectors.toList());

        return UserEntity.builder()
                .name(userDTO.getName())
                .cpf(userDTO.getCpf())
                .email(userDTO.getEmail())
                .vehicles(vehicleEntityList)
                .build();
    }

}