package cz.siemens.inventory.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cz.siemens.inventory.dao.DeviceDaoImpl;
import cz.siemens.inventory.dao.GenericDao;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
	@JoinColumn(name = "holder_id", referencedColumnName = "id")
	private User owner;

	@Column(name = "date_add")
	@Type(type = "timestamp")
	private Date addDate;

	@ManyToOne
	@JoinColumn(name = "status", referencedColumnName = "id")
	private DeviceState deviceState;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "deviceInventory", cascade = CascadeType.ALL)
	private InventoryRecord inventoryRecord;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "deviceRevision", cascade = CascadeType.ALL)
	private ApplianceRevision lastRevision;

	public Device() {
		InventoryRecord inventoryRecord = new InventoryRecord();
		inventoryRecord.setDeviceInventory(this);
		this.setInventoryRecord(inventoryRecord);

		ApplianceRevision revision = new ApplianceRevision();
		revision.setDevice(this);
		this.setLastRevision(revision);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public DeviceType getObjectType() {
		return objectType;
	}

	public void setObjectType(DeviceType objectType) {
		this.objectType = objectType;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getBarcodeNumber() {
		return barcodeNumber == null ? "" : barcodeNumber;
	}

	public void setBarcodeNumber(String barcodeNumber) {
		this.barcodeNumber = barcodeNumber;
	}

	public CompanyOwner getCompanyOwner() {
		return companyOwner;
	}

	public void setCompanyOwner(CompanyOwner companyOwner) {
		this.companyOwner = companyOwner;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public DeviceState getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(DeviceState deviceState) {
		this.deviceState = deviceState;
	}

	public InventoryRecord getInventoryRecord() {
		return inventoryRecord;
	}

	public void setInventoryRecord(InventoryRecord inventoryRecord) {
		this.inventoryRecord = inventoryRecord;
	}

	public ApplianceRevision getLastRevision() {
		return lastRevision;
	}

	public void setLastRevision(ApplianceRevision lastRevision) {
		this.lastRevision = lastRevision;
	}

	/********** VLASTNI GETTRY ************/

	public String getObjectTypeName() {
		return objectType != null ? objectType.getObjectTypeName() : undefStr;
	}

	public String getObjectTypeVersion() {
		return objectType != null ? objectType.getVersion() : undefStr;
	}

	public String getCompanyOwnerName() {
		return companyOwner != null ? companyOwner.getName() : undefStr;
	}

	public String getDepartmentName() {
		return department != null ? department.getName() : undefStr;
	}

	public String getProjectName() {
		return project != null ? project.getName() : undefStr;
	}

	public String getOwnerName() {
		return owner != null ? owner.getName() : undefStr;
	}
	
	public String getHolderName() {
		return "< Test Testoj >";
	}

	public String getDateString() {
		return new SimpleDateFormat("yyyy-MM-dd").format(getAddDate());
	}

	public LocalDate getLocalDate() {
		if (addDate != null) {
			return addDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		} else {
			return LocalDate.now();
		}
	}

	public void setLocalDate(LocalDate localDate) {
		this.addDate = java.sql.Date.valueOf(localDate);
	}

	public String getDeviceStateName() {
		return deviceState != null ? deviceState.getName() : undefStr;
	}

	/********** LOGIKA PRO INVENTURY ************/

	public String getInventoryState() {
		InventoryRecord record = getInventoryRecord();
		if (record == null) {
			record = new InventoryRecord();
			record.setRegistered(false);
			record.setDeviceInventory(this);
			this.setInventoryRecord(record);
			//todo: fix
//			GenericDao<Device> deviceDao = GenericDao.createGenericDao(Device.class);
//			deviceDao.update(this);
		}
//todo: fixxx
		return "";
		// Nastavi spravny symbol pro zobrazeni do tabulky
//		if (record.isRegistered())
//			return VaadinIcons.CHECK.getHtml();
//		else
//			return VaadinIcons.CLOSE.getHtml();
	}

	/********** LOGIKA PRO ELEKTROREVIZE ************/

	public LocalDate getLastRevisionDate() {
		ApplianceRevision revision = getLastRevision();
		if (revision == null) {
			revision = new ApplianceRevision();
			revision.setDevice(this);
			this.setLastRevision(revision);
			//todo: fix
			GenericDao<Device> deviceDao = new DeviceDaoImpl();
			deviceDao.update(this);
		}
		LocalDate lastRevDate = revision.getLastRevision();
		return lastRevDate;
	}

	public String getLastRevisionDateString() {
		LocalDate lastRevDate = getLastRevisionDate();
		return lastRevDate == null ? "" : lastRevDate.toString();
	}

	public void setLastRevisionDate(LocalDate newRevDate) {
		ApplianceRevision revision = getLastRevision();
		revision.setLastRevision(newRevDate);
	}

	public double getRevisionPeriod() {
		ApplianceRevision revision = getLastRevision();
		if (revision != null)
			return revision.getInterval();
		else
			return 0;
	}

	public String getRevisionPeriodString() {
		int period = (int) getRevisionPeriod();
		if (period == 0)
			return "<TBD>";
		else {
			String result = Integer.toString(period) + " year";
			if (period > 1)
				result += "s";
			return result;
		}
	}

	public void setRevisionPeriod(double interval) {
		ApplianceRevision revision = getLastRevision();
		revision.setInterval((byte) interval);
	}

	public String getLeftDaysCount() {
		LocalDate lastDate = getLastRevisionDate();
		long period = (long) getRevisionPeriod();
		if (lastDate != null && period > 0) {
			LocalDate currentDate = LocalDate.now();
			LocalDate newDate = lastDate.plusYears(period);
			if(newDate.isBefore(currentDate)) {
				return "0";
			}
			long daysBetween = Duration.between(currentDate.atStartOfDay(), newDate.atStartOfDay()).toDays();
			return Long.toString(daysBetween);
		}
		return "";
	}
}