package cz.siemens.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || !(o instanceof DeviceState)) return false;

		DeviceState deviceState = (DeviceState) o;

		return getName().equals(deviceState.getName());
	}

	@Override
	public int hashCode() {
		int result = 31 * getName().hashCode();
		result = 31 * result + getName().hashCode();
		return result;
	}
}
