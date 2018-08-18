package cz.siemens.inventory.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import javax.persistence.*;

@Data
@Entity
@Table(name = "device_types")
public class DeviceType implements Serializable {

	private static final long serialVersionUID = -6705403290786927858L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "object_type")
	private String objectTypeName;

	private Integer classification;

	private String manufacturer;

	@Column(name = "order_no")
	private String orderNumber;

	private String version;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "supplier", referencedColumnName = "id")
	private Supplier supplier;

	private Double price;

	public DeviceType() {
		classification = 1;
		manufacturer = "SIEMENS";
	}

	public String getTypeAndVersionName() {
		if (version != null && version.length() > 0)
			return getObjectTypeName() + ", Version: " + getVersion();
		else
			return objectTypeName;
	}
}
