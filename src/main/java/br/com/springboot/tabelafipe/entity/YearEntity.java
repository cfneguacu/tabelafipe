package br.com.springboot.tabelafipe.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_ano" , sequenceName = "seq_ano", allocationSize = 1)
public class YearEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ano")
    @Column(name = "ANO_ID")
    private Long id;

    private String code;

    private String name;

}
