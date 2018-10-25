package cz.siemens.inventory.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "calibration")
@ToString(exclude = "deviceCalibration")
public class ApplianceCalibration implements Serializable
{
	private static final long serialVersionUID = -5444360754207233467L;

	@Id
	@GeneratedValue(generator = "calibrationGenerator")
	@GenericGenerator(name = "calibrationGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "deviceCalibration"))
	private Long id;

	@Column(name = "calib_interval")
	private Integer interval;

	@Column(name = "last_calibration")
	private LocalDate lastCalibration;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private DeviceInternal deviceCalibration;
}