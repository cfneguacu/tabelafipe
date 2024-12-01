package br.com.springboot.tabelafipe.entity;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_characteristic" , sequenceName = "seq_characteristic", allocationSize = 1)
public class CharacteristicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_characteristic")
    @Column(name = "CHARACTERISTIC_ID")
    private Long id;

    private String price;

    private String model;

    private String modelYear;

    private String fuel;

    private String brand;
}
