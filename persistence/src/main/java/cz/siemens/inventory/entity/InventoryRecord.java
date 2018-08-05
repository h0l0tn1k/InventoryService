package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import javax.persistence.*;

@Data
@NoArgsConstructor
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
}