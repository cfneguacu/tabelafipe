package br.com.springboot.tabelafipe.model;

import br.com.springboot.tabelafipe.status.Status;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_veiculos" , sequenceName = "seq_veiculos", allocationSize = 1)
public class Veiculo implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculos")
	@Column(name = "VEICULO_ID")
	private Long id;

	private LocalDate data;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="TB_VEICULO_MODELO",joinColumns =
	@JoinColumn( name = "VEICULO_ID",referencedColumnName="VEICULO_ID"),
			inverseJoinColumns = @JoinColumn(name = "MODELO_ID",referencedColumnName="MODELO_ID"))
	private Modelo modelo_id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="TB_VEICULO_ANO",joinColumns =
	@JoinColumn( name = "VEICULO_ID",referencedColumnName="VEICULO_ID"),
			inverseJoinColumns = @JoinColumn(name = "ANO_ID",referencedColumnName="ANO_ID"))
	private Ano ano_id;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="TB_VEICULO_USUARIO",joinColumns =
	@JoinColumn( name = "VEICULO_ID",referencedColumnName="VEICULO_ID"),
			inverseJoinColumns = @JoinColumn(name = "USUARIO_ID",referencedColumnName="USUARIO_ID"))
	private Usuario usuario_id;

	@Column(unique = true)
	private String renavam;

	private String cor;

	private String combustivel;

	@Column(unique = true)
	private String placa;

	private Status status;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="TB_VEICULO_CARACTERISTICA",joinColumns =
	@JoinColumn( name = "VEICULO_ID",referencedColumnName="VEICULO_ID"),
			inverseJoinColumns = @JoinColumn(name = "CARACTERISTICA_ID",referencedColumnName="CARACTERISTICA_ID"))
	private Caracteristica caracteristica_id;


}
	

	