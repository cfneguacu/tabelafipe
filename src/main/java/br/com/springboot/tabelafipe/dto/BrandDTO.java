package br.com.springboot.tabelafipe.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandDTO {

    private Long id;

    private String code;

    private String name;

}
