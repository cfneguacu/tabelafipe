package br.com.springboot.tabelafipe.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_caracteristica" , sequenceName = "seq_caracteristica", allocationSize = 1)
public class Caracteristica {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_caracteristica")
    @Column(name = "CARACTERISTICA_ID")
    private Long id;

    private String price;

    private String model;

    private String modelYear;

    private String fuel;

    private String brand;
}
