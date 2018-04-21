package cz.siemens.inventory.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "holder_changelog")
public class HolderChangelog implements Serializable
{
	private static final long serialVersionUID = 6620547551297893250L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "date")
	@Type(type = "timestamp")
	private Timestamp changeDate;
	
	@ManyToOne
	@JoinColumn(name = "object_id", referencedColumnName = "id")
	private Device device;

	@ManyToOne
	@JoinColumn(name = "old_holder_id", referencedColumnName = "id")
	private UserScd oldHolder;

	@ManyToOne
	@JoinColumn(name = "new_holder_id", referencedColumnName = "id")
	private UserScd newHolder;
	
	@Column(name = "comment")
	private String comment;
	
	public HolderChangelog()
	{}
	
	public HolderChangelog(Device device)
	{
		this.device = device;
		if(device.getHolder() != null)
			this.oldHolder = device.getHolder();
		else
			this.oldHolder = device.getOwner();
	}
	
	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public Timestamp getChangeDate()
	{
		return changeDate;
	}
	
	public String getChangeDateString()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = dateFormat.format(changeDate);
		return dateStr;
	}

	public void setChangeDate(Timestamp changeDate)
	{
		this.changeDate = changeDate;
	}
	
	public void setChangeLocalDate(LocalDateTime localDate)
	{
		this.changeDate = Timestamp.valueOf(localDate);
	}

	public Device getDevice()
	{
		return device;
	}

	public void setDevice(Device device)
	{
		this.device = device;
	}

	public UserScd getOldHolder()
	{
		return oldHolder;
	}
	
	public String getOldHolderName()
	{
		return oldHolder.getName();
	}

	public void setOldHolder(UserScd oldHolder)
	{
		this.oldHolder = oldHolder;
	}

	public UserScd getNewHolder()
	{
		return newHolder;
	}
	
	public String getNewHolderName()
	{
		return newHolder.getName();
	}

	public void setNewHolder(UserScd newHolder)
	{
		this.newHolder = newHolder;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
}
