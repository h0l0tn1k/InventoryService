package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "departments")
public class Department implements Serializable {

	private static final long serialVersionUID = 1496942111943440900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;

}