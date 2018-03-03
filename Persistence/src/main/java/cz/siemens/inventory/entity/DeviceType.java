package cz.siemens.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "device_types")
public class DeviceType implements Serializable {

	private static final long serialVersionUID = -6705403290786927858L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "object_type")
	private String objectTypeName;

	private Integer classification;

	private String manufacturer;

	@Column(name = "order_no")
	private String orderNumber;

	private String version;

	@ManyToOne
	@JoinColumn(name = "supplier", referencedColumnName = "id")
	private Supplier supplier;

	private Double price;

	public DeviceType() {
		classification = 1;
		manufacturer = "SIEMENS";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getObjectTypeName() {
		return objectTypeName;
	}

	public void setObjectTypeName(String objectTypeName) {
		this.objectTypeName = objectTypeName;
	}

	public Integer getClassification() {
		return classification;
	}

	public void setClassification(Integer classification) {
		this.classification = classification;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	/********** OWN GETTERS & SETTERS ************/

	public String getTypeAndVersionName() {
		if (version != null && version.length() > 0)
			return getObjectTypeName() + ", Version: " + getVersion();
		else
			return objectTypeName;
	}
		
	public String getSupplierName() {
		return supplier != null ? supplier.getName() : "";
	}
}
