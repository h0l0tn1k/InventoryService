package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable {

	private static final long serialVersionUID = -3450935247262431037L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "short_name")
	private String shortName;

	@Column(name = "holder_type")
	private String holderType;

	@Column(name = "org_unit")
	private String orgUnit;

	@Column(name = "barcode_no")
	private String barcode;

	private boolean enabled;
}