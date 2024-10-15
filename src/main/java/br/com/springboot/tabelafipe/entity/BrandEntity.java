package br.com.springboot.tabelafipe.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_marcas" , sequenceName = "seq_marcas", allocationSize = 1)
public class BrandEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_marcas")
    @Column(name = "MARCA_ID")
    private Long id;

    private String code;

    private String name;

}
