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
@SequenceGenerator(name = "seq_models" , sequenceName = "seq_models", allocationSize = 1)
public class ModelEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_models")
    @Column(name = "MODEL_ID")
    private Long id;

    private String code;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
        @JoinTable(name="TB_BRAND_MODEL",joinColumns =
            @JoinColumn( name = "MODEL_ID",referencedColumnName="MODEL_ID"),
                inverseJoinColumns = @JoinColumn(name = "BRAND_ID",referencedColumnName="BRAND_ID"))
    private BrandEntity brandEntity;


}
