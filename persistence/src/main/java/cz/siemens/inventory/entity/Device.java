package cz.siemens.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.time.OffsetDateTime;

@Data
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name = "objects")
@ToString
public class Device implements Serializable {

	private static final long serialVersionUID = 6243936014818205991L;
	private static final String undefStr = "";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "object_type_id", referencedColumnName = "id")
	private DeviceType objectType;

	@Column(name = "serial_no")
	private String serialNumber;

	@Column(name = "inventory_no")
	private String inventoryNumber;

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
	private LoginUserScd owner;

	@ManyToOne
	@JoinColumn(name = "holder_id", referencedColumnName = "id")
	private LoginUserScd holder;

	@Column(name = "default_location")
	private String defaultLocation;

	@CreationTimestamp
	@Column(name = "date_add", updatable = false, nullable = false)
	private OffsetDateTime addDate;

	@ManyToOne
	@JoinColumn(name = "state", referencedColumnName = "id")
	private DeviceState deviceState;

	@Column(name = "comment")
	private String comment;

	@Column(name = "nst")
	private String nstValue;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "inventoryRecord_id", referencedColumnName = "id")
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
		revision.setDeviceRevision(this);
		this.setLastRevision(revision);

		ApplianceCalibration calibration = new ApplianceCalibration();
		calibration.setDeviceCalibration(this);
		this.setDeviceCalibration(calibration);
	}

	public String getTypeAndVersionName() {
		return objectType != null ? objectType.getTypeAndVersionName() : undefStr;
	}

	public void setInventoryRecord(InventoryRecord inventoryRecord) {
		this.inventoryRecord = inventoryRecord;
		inventoryRecord.setDeviceInventory(this);
	}

	public void setLastRevision(ApplianceRevision lastRevision) {
		lastRevision.setDeviceRevision(this);
		this.lastRevision = lastRevision;
	}

	public void setDeviceCalibration(ApplianceCalibration deviceCalibration) {
		deviceCalibration.setDeviceCalibration(this);
		this.deviceCalibration = deviceCalibration;
	}

}