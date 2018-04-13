package cz.siemens.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "projects")
public class Project implements Serializable {

	private static final long serialVersionUID = -6533944325548853861L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	public Project() {
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