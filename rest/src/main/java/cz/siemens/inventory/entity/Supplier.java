package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

	private static final long serialVersionUID = -273712401597455162L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
}
