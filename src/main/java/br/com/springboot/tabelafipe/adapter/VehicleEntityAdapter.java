package br.com.springboot.tabelafipe.adapter;

import br.com.springboot.tabelafipe.convert.InstantConvert;
import br.com.springboot.tabelafipe.dto.VehicleDTO;
import br.com.springboot.tabelafipe.entity.*;
import br.com.springboot.tabelafipe.status.Status;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

import static br.com.springboot.tabelafipe.utils.VehicleUtils.getActiveRelayTemp;

@Component
public class VehicleEntityAdapter {

    private InstantConvert instantConvert;

    public VehicleEntity toModel(VehicleDTO vehicleDTO) {

        int activeRelayTemp = getActiveRelayTemp(vehicleDTO.getLicensePlate());

        return VehicleEntity.builder()
                .date(instantConvert.convertStringToInstant(vehicleDTO.getDate()))
                .modelEntity(ModelEntity.builder()
                        .brandEntity(BrandEntity.builder()
                                .code(vehicleDTO.getModelDTO().getBrandDTO().getCode())
                                .build())
                        .code(vehicleDTO.getModelDTO().getCode())
                        .build())
                .yearEntity(YearEntity.builder()
                        .code(vehicleDTO.getYearDTO().getCode())
                        .build())
                .characteristicEntity(CharacteristicEntity.builder()
                        .model(vehicleDTO.getCharacteristicDTO().getModel())
                        .brand(vehicleDTO.getCharacteristicDTO().getBrand())
                        .fuel(vehicleDTO.getCharacteristicDTO().getFuel())
                        .modelYear(vehicleDTO.getCharacteristicDTO().getModelYear())
                        .price(vehicleDTO.getCharacteristicDTO().getPrice())
                        .id(vehicleDTO.getCharacteristicDTO().getId())
                        .build())
                .color(vehicleDTO.getColor())
                .fuel(vehicleDTO.getFuel())
                .status(Status.PENDING)
                .activeRelay(activeRelayTemp == LocalDate.now().getDayOfWeek().getValue())
                .relay(DayOfWeek.of(activeRelayTemp).name())
                .build();
    }

}
