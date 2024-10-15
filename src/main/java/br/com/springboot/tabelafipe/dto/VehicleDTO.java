package br.com.springboot.tabelafipe.dto;

import br.com.springboot.tabelafipe.entity.YearEntity;
import br.com.springboot.tabelafipe.entity.CharacteristicEntity;
import br.com.springboot.tabelafipe.entity.ModelEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VehicleDTO {

    private Long id;

    private YearDTO yearDTO;

    private CharacteristicDTO characteristicDTO;

    private String fuel;

    private String color;

    private String date;

    private ModelDTO modelDTO;

    private String licensePlate;

    private String renavam;

}
