package br.com.springboot.tabelafipe.dto;

import br.com.springboot.tabelafipe.entity.YearEntity;
import br.com.springboot.tabelafipe.entity.CharacteristicEntity;
import br.com.springboot.tabelafipe.entity.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {

    private Long id;

    private YearDTO yearDTO;

    private CharacteristicDTO characteristicDTO;

    private String fuel;

    @NotEmpty(message = "Cor is mandatory")
    private String color;

    @NotEmpty(message = "Date cannot empty, please inform!")
    private String date;

    private ModelDTO modelDTO;

    @NotEmpty(message = "Placa is mandatory")
    private String licensePlate;

    @NotEmpty(message = "Renavam is mandatory")
    @Size(min = 11, max = 11, message = "Renavam contains 11 digits")
    private String renavam;

}
