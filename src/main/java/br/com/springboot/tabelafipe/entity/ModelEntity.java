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
@SequenceGenerator(name = "seq_modelos" , sequenceName = "seq_modelos", allocationSize = 1)
public class ModelEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_modelos")
    @Column(name = "MODELO_ID")
    private Long id;

    private String code;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
        @JoinTable(name="TB_MARCA_MODELO",joinColumns =
            @JoinColumn( name = "MODELO_ID",referencedColumnName="MODELO_ID"),
                inverseJoinColumns = @JoinColumn(name = "MARCA_ID",referencedColumnName="MARCA_ID"))
    private BrandEntity brandEntityId;


}
