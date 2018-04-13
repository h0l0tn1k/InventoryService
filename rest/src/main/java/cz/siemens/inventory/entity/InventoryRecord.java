package cz.siemens.inventory.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "inventory")
public class InventoryRecord implements Serializable {
	
	private static final long serialVersionUID = -4889459620132820143L;

	@Id
	@GeneratedValue(generator = "inventoryGenerator")
	@GenericGenerator(name = "inventoryGenerator", strategy = "foreign", parameters = @Parameter(name = "property", value = "deviceInventory"))
	private long id;
	
	private boolean registered;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private Device deviceInventory;
	
	public InventoryRecord() {}

	public InventoryRecord(long id, boolean registered) {
		this.id = id;
		this.registered = registered;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isRegistered() {
		return registered;
	}

	public void setRegistered(boolean registered) {
		this.registered = registered;
	}

	public Device getDeviceInventory() {
		return deviceInventory;
	}

	public void setDeviceInventory(Device deviceInventory) {
		this.deviceInventory = deviceInventory;
	}
}