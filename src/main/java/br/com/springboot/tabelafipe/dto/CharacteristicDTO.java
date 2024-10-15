package br.com.springboot.tabelafipe.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CharacteristicDTO {

    private Long id;

    private String price;

    private String model;

    private String modelYear;

    private String fuel;

    private String brand;
}
