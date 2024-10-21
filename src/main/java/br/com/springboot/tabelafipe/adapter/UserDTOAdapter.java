package br.com.springboot.tabelafipe.adapter;

import br.com.springboot.tabelafipe.convert.InstantConvert;
import br.com.springboot.tabelafipe.dto.*;
import br.com.springboot.tabelafipe.entity.UserEntity;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOAdapter {

    private InstantConvert instantConvert;

    private VehicleDTOAdapter vehicleDTOAdapter;
    
    public UserDTO toDTO(UserEntity userEntity) {

        List<VehicleDTO> vehicleDTOList = new ArrayList<>();
        List<VehicleEntity> vehicleEntityList = userEntity.getVehicles();

        vehicleDTOList = vehicleEntityList.stream()
                .map(vehicleDTOAdapter::toDTO)
                .collect(Collectors.toList());

        /*for (VehicleEntity vehicle : vehicleEntityList){
            VehicleDTO vehicleDTO = VehicleDTO.builder()
                    .yearDTO(YearDTO.builder()
                            .name(vehicle.getYearEntity().getName())
                            .code(vehicle.getYearEntity().getCode())
                            .build())
                    .id(vehicle.getId())
                    .color(vehicle.getColor())
                    .licensePlate(vehicle.getLicensePlate())
                    .fuel(vehicle.getFuel())
                    .date(instantConvert.convertInstantToString(vehicle.getDate()))
                    .modelDTO(ModelDTO.builder()
                            .name(vehicle.getModelEntity().getName())
                            .code(vehicle.getModelEntity().getCode())
                            .brandDTO(BrandDTO.builder()
                                    .code(vehicle.getModelEntity().getBrandEntityId().getCode())
                                    .name(vehicle.getModelEntity().getBrandEntityId().getName())
                                    .build())
                            .build())
                    .renavam(vehicle.getRenavam())
                    .characteristicDTO(CharacteristicDTO.builder()
                            .brand(vehicle.getCharacteristicEntity().getBrand())
                            .model(vehicle.getCharacteristicEntity().getModel())
                            .price(vehicle.getCharacteristicEntity().getPrice())
                            .modelYear(vehicle.getCharacteristicEntity().getModelYear())
                            .fuel(vehicle.getCharacteristicEntity().getFuel())
                            .build())
                    .build();
            vehicleDTOList.add(vehicleDTO);
        }*/

        return UserDTO.builder()
                .name(userEntity.getName())
                .cpf(userEntity.getCpf())
                .email(userEntity.getEmail())
                .vehicles(vehicleDTOList)
                .build();
    }

}
