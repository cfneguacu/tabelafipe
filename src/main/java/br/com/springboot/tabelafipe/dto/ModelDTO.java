package br.com.springboot.tabelafipe.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class ModelDTO {

    private Long id;

    private String code;

    private String name;

    private BrandDTO brandDTO;

}
