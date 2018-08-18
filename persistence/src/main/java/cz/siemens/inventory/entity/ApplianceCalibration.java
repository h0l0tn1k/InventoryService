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

import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
	private byte interval;

	@Column(name = "last_calibration")
	private LocalDate lastCalibration;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Device deviceCalibration;

	public ApplianceCalibration()
	{
	}

	public ApplianceCalibration(long id, byte interval)
	{
		this.id = id;
		this.interval = interval;
	}

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public byte getInterval()
	{
		return interval;
	}

	public void setInterval(byte interval)
	{
		this.interval = interval;
	}

	public LocalDate getLastCalibration()
	{
		return lastCalibration;
	}

	public void setLastCalibration(LocalDate lastCalibration)
	{
		this.lastCalibration = lastCalibration;
	}

	public Device getDevice()
	{
		return deviceCalibration;
	}

	public void setDevice(Device device)
	{
		this.deviceCalibration = device;
	}
}