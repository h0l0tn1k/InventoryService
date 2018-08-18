package cz.siemens.inventory.entity;

import cz.siemens.inventory.entity.custom.InventoryState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Entity
@Table(name = "inventory")
@ToString(exclude = "deviceInventory")
public class InventoryRecord implements Serializable {

	private static final long serialVersionUID = -4889459620132820143L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private InventoryState registered;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "inventoryRecord")
	private Device deviceInventory;
}