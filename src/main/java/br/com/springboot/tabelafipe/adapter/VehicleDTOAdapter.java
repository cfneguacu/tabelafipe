package br.com.springboot.tabelafipe.adapter;

import br.com.springboot.tabelafipe.convert.InstantConvert;
import br.com.springboot.tabelafipe.dto.*;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.status.Status;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class VehicleDTOAdapter {


    private InstantConvert instantConvert;

    public VehicleDTO toDTO(VehicleEntity vehicle){

        return VehicleDTO.builder()
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
                        .brand(vehicle.getCharacteristicEntityId().getBrand())
                        .model(vehicle.getCharacteristicEntityId().getModel())
                        .price(vehicle.getCharacteristicEntityId().getPrice())
                        .modelYear(vehicle.getCharacteristicEntityId().getModelYear())
                        .fuel(vehicle.getCharacteristicEntityId().getFuel())
                        .build())
                .build();

    }



    private String getStatusClass(Status status) {
        return switch (status) {
            case PENDING -> "badge badge-primary";
            case SUCCESS -> "badge badge-success";
            case FAILURE -> "badge badge-danger";
            default -> "badge badge-secondary";
        };
    }

}
