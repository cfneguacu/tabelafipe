package br.com.springboot.tabelafipe.dto;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserDTO {

    private Long id;

    private String cpf;

    private String email;

    private String name;

    private List<VehicleDTO> vehicles;

}
