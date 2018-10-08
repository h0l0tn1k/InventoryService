package cz.siemens.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "revision")
@ToString(exclude = "deviceRevision")
public class ApplianceRevision implements Serializable {

	private static final long serialVersionUID = 2448457913612961446L;

	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@GeneratedValue(generator = "revisionGenerator")
//	@GenericGenerator(name = "revisionGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "deviceRevision"))
	private Long id;
	
	@Column(name = "rev_interval")
	private Integer interval;
	
	@Column(name = "last_revision")
	private LocalDate lastRevision;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	@MapsId
	private Device deviceRevision;
}