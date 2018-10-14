package cz.siemens.inventory.entity;

import cz.siemens.inventory.entity.custom.InventoryState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import org.hibernate.annotations.Parameter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "inventory")
@ToString(exclude = "deviceInventory")
public class InventoryRecord implements Serializable {

	private static final long serialVersionUID = -4889459620132820143L;

	@Id
	@GeneratedValue(generator = "inventoryGenerator")
	@GenericGenerator(name = "inventoryGenerator", strategy = "foreign", parameters = { @Parameter(name = "property", value = "deviceInventory") } )
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private InventoryState registered;

	@Column(name = "comment")
	private String comment;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id")
	@MapsId
	private DeviceInternal deviceInventory;
}