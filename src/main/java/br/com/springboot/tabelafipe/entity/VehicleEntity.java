package br.com.springboot.tabelafipe.entity;

import br.com.springboot.tabelafipe.status.Status;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@SequenceGenerator(name = "seq_vehicles" , sequenceName = "seq_vehicles", allocationSize = 1)
public class VehicleEntity implements Serializable{

	@Serial
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehicles")
	@Column(name = "VEHICLE_ID")
	private Long id;

	private Instant date;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="TB_VEHICLE_MODEL",joinColumns =
	@JoinColumn( name = "VEHICLE_ID",referencedColumnName="VEHICLE_ID"),
			inverseJoinColumns = @JoinColumn(name = "MODEL_ID",referencedColumnName="MODEL_ID"))
	private ModelEntity modelEntity;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="TB_VEHICLE_YEAR",joinColumns =
	@JoinColumn( name = "VEHICLE_ID",referencedColumnName="VEHICLE_ID"),
			inverseJoinColumns = @JoinColumn(name = "YEAR_ID",referencedColumnName="YEAR_ID"))
	private YearEntity yearEntity;
	
	@Column(unique = true)
	private String renavam;

	private String color;

	private String fuel;

	@Column(unique = true)
	private String licensePlate;

	private Status status;

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH})
	@JoinTable(name="TB_VEHICLE_CHARACTERISTIC",joinColumns =
	@JoinColumn( name = "VEHICLE_ID",referencedColumnName="VEHICLE_ID"),
			inverseJoinColumns = @JoinColumn(name = "CHARACTERISTIC_ID",referencedColumnName="CHARACTERISTIC_ID"))
	private CharacteristicEntity characteristicEntity;

	private String relay;

	private boolean activeRelay;


}
	

	