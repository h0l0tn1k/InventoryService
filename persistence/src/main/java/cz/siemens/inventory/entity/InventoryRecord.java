package cz.siemens.inventory.entity;

import cz.siemens.inventory.entity.custom.InventoryState;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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

	@Column(name = "comment")
	private String comment;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "inventoryRecord")
	private Device deviceInventory;
}