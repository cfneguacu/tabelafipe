package br.com.springboot.tabelafipe.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import jakarta.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_user" , sequenceName = "seq_user", allocationSize = 1)
public class UserEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user")
    @Column(name = "USER_ID")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String cpf;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name="TB_VEHICLES_USER",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "VEHICLE_ID",referencedColumnName = "VEHICLE_ID")
    )
    private List<VehicleEntity> vehicles;

}
