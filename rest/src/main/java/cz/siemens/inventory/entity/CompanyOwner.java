package cz.siemens.inventory.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "company_owners")
public class CompanyOwner implements Serializable {

	private static final long serialVersionUID = -3307622710029349065L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;

	public CompanyOwner() {
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
		if (o == null || !(o instanceof CompanyOwner)) return false;
		CompanyOwner companyOwner = (CompanyOwner) o;

		return getName().equals(companyOwner.getName());
	}
	@Override
	public int hashCode() {
		int result = 31 * getName().hashCode();
		result = 31 * result + getName().hashCode();
		return result;
	}
}
