package br.com.springboot.tabelafipe.model;

import lombok.*;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_veiculos" , sequenceName = "seq_veiculos", allocationSize = 1)
public class Veiculos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculos")
	private Long codigo;

	private String marca;
	private String modelo;
	private String ano;

	@Column(unique = true)
	private String renavam;

	private String cor;

	@Column(unique = true)
	private String placa;

}
	

	