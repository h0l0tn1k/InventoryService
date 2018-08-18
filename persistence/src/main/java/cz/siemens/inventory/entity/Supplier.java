package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {

	private static final long serialVersionUID = -273712401597455162L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
}
