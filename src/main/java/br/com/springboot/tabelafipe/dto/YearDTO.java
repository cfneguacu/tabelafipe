package br.com.springboot.tabelafipe.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class YearDTO {

    private Long id;

    private String code;

    private String name;

}
