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

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

@Data
@NoArgsConstructor
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
}
