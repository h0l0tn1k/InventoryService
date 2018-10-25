package cz.siemens.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Parameter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "revision")
@ToString(exclude = "deviceRevision")
public class ApplianceRevision implements Serializable {

	private static final long serialVersionUID = 2448457913612961446L;

	@Id
	@GeneratedValue(generator = "revisionGenerator")
	@GenericGenerator(name = "revisionGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "deviceRevision"))
	private Long id;
	
	@Column(name = "rev_interval")
	private Integer interval;
	
	@Column(name = "last_revision")
	private LocalDate lastRevision;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	@MapsId
	private DeviceInternal deviceRevision;
}