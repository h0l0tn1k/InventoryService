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

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "callibration")
public class ApplianceCallibration implements Serializable
{
	private static final long serialVersionUID = -5444360754207233467L;

	@Id
	@GeneratedValue(generator = "callibrationGenerator")
	@GenericGenerator(name = "callibrationGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "deviceCallibration"))
	private long id;

	@Column(name = "callib_interval")
	private byte interval;

	@Column(name = "last_callibration")
	private LocalDate lastCallibration;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Device deviceCallibration;

	public ApplianceCallibration()
	{
	}

	public ApplianceCallibration(long id, byte interval)
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

	public LocalDate getLastCallibration()
	{
		return lastCallibration;
	}

	public void setLastCallibration(LocalDate lastCallibration)
	{
		this.lastCallibration = lastCallibration;
	}

	public Device getDevice()
	{
		return deviceCallibration;
	}

	public void setDevice(Device device)
	{
		this.deviceCallibration = device;
	}
}