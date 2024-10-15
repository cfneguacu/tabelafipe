package br.com.springboot.tabelafipe.adapter;

import br.com.springboot.tabelafipe.dto.UserDTO;
import br.com.springboot.tabelafipe.dto.VehicleDTO;
import br.com.springboot.tabelafipe.entity.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class VehicleEntityAdapter {

    public VehicleEntity toModel(VehicleDTO vehicleDTO) {

        return VehicleEntity.builder()
                .date(convertStringToInstant(vehicleDTO.getDate()))
                .modelEntityId(ModelEntity.builder()
                        .brandEntityId(BrandEntity.builder()
                                .code(vehicleDTO.getModelDTO().getBrandDTO().getCode())
                                .build())
                        .build())
                .yearEntityId(YearEntity.builder()
                        .code(vehicleDTO.getYearDTO().getCode())
                        .build())
                .characteristicEntityId(CharacteristicEntity.builder()
                        .model(vehicleDTO.getCharacteristicDTO().getModel())
                        .brand(vehicleDTO.getCharacteristicDTO().getBrand())
                        .fuel(vehicleDTO.getCharacteristicDTO().getFuel())
                        .modelYear(vehicleDTO.getCharacteristicDTO().getModelYear())
                        .price(vehicleDTO.getCharacteristicDTO().getPrice())
                        .id(vehicleDTO.getCharacteristicDTO().getId())
                        .build())
                .color(vehicleDTO.getColor())
                .fuel(vehicleDTO.getFuel())
                .build();
    }

    public Instant convertStringToInstant(final String dateString){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(dateString);
            return date.toInstant();
        }catch (ParseException pe){
            throw new IllegalArgumentException("Error during date parse "+ pe.getMessage());
        }
    }
}
