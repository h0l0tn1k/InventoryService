package cz.siemens.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

	private static final long serialVersionUID = -273712401597455162L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	public Supplier() {
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
