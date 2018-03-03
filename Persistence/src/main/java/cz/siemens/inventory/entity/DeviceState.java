package cz.siemens.inventory.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "device_states")
public class DeviceState implements Serializable {

	private static final long serialVersionUID = 4057811528549202400L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	public DeviceState() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
