package br.com.springboot.tabelafipe.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_year" , sequenceName = "seq_year", allocationSize = 1)
public class YearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_year")
    @Column(name = "YEAR_ID")
    private Long id;

    private String code;

    private String name;

}
