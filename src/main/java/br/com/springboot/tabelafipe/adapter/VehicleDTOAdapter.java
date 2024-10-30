package br.com.springboot.tabelafipe.adapter;

import br.com.springboot.tabelafipe.convert.InstantConvert;
import br.com.springboot.tabelafipe.dto.*;
import br.com.springboot.tabelafipe.entity.CharacteristicEntity;
import br.com.springboot.tabelafipe.entity.VehicleEntity;
import br.com.springboot.tabelafipe.status.Status;
import br.com.springboot.tabelafipe.utils.VehicleUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Component
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
                                .code(vehicle.getModelEntity().getBrandEntity().getCode())
                                .name(vehicle.getModelEntity().getBrandEntity().getName())
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
