package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "company_owners")
public class CompanyOwner implements Serializable {

	private static final long serialVersionUID = -3307622710029349065L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
}
