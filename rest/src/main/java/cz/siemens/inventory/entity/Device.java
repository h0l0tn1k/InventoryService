package cz.siemens.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.siemens.inventory.dao.DeviceDaoImpl;
import cz.siemens.inventory.dao.GenericDao;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Table(name = "objects")
public class Device implements Serializable {

	private static final long serialVersionUID = 6243936014818205991L;
	private static final String undefStr = "";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "object_type_id", referencedColumnName = "id")
	private DeviceType objectType;

	@Column(name = "serial_no")
	private String serialNumber;

	@Column(name = "barcode_no")
	private String barcodeNumber;

	@ManyToOne
	@JoinColumn(name = "company_owner", referencedColumnName = "id")
	private CompanyOwner companyOwner;

	@ManyToOne
	@JoinColumn(name = "department_owner", referencedColumnName = "id")
	@NotFound(action = NotFoundAction.IGNORE)
	private Department department;

	@ManyToOne
	@JoinColumn(name = "project_owner", referencedColumnName = "id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "owner_id", referencedColumnName = "id")
	private UserScd owner;

	@ManyToOne
	@JoinColumn(name = "holder_id", referencedColumnName = "id")
	private UserScd holder;

	@Column(name = "default_location")
	private String defaultLocation;

	@Column(name = "date_add")
	private OffsetDateTime addDate;

	@ManyToOne
	@JoinColumn(name = "state", referencedColumnName = "id")
	private DeviceState deviceState;

	@Column(name = "comment")
	private String comment;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "deviceInventory", cascade = CascadeType.ALL)
	private InventoryRecord inventoryRecord;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "deviceRevision", cascade = CascadeType.ALL)
	private ApplianceRevision lastRevision;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "deviceCalibration", cascade = CascadeType.ALL)
	private ApplianceCalibration deviceCalibration;

	public Device() {
		InventoryRecord inventoryRecord = new InventoryRecord();
		inventoryRecord.setDeviceInventory(this);
		this.setInventoryRecord(inventoryRecord);

		ApplianceRevision revision = new ApplianceRevision();
		revision.setDevice(this);
		this.setLastRevision(revision);

		ApplianceCalibration calibration = new ApplianceCalibration();
		calibration.setDevice(this);
		this.setDeviceCalibration(calibration);
	}

	public String getTypeAndVersionName()
	{
		return objectType != null ? objectType.getTypeAndVersionName() : undefStr;
	}

}