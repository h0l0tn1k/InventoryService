package cz.siemens.inventory.controllers.mapper;

import cz.siemens.inventory.entity.DeviceType;
import cz.siemens.inventory.entity.Supplier;
import cz.siemens.inventory.mapper.DeviceTypeMapper;
import cz.siemens.inventory.mapper.DeviceTypeMapper;
import cz.siemens.inventory.mapper.impl.DeviceTypeMapperImpl;
import cz.siemens.inventory.mapper.impl.SupplierMapperImpl;
import cz.siemens.inventory.mapper.impl.DeviceTypeMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceTypeMapperTest {

	private DeviceTypeMapper cut = new DeviceTypeMapperImpl(new SupplierMapperImpl());

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<DeviceType> deviceTypes = cut.mapToInternal(new ArrayList<>());
		assertThat(deviceTypes).isEmpty();
	}

	@Test
	public void test_nullDeviceType_mapToInternal() {
		cz.siemens.inventory.gen.model.DeviceType nullDeviceType = null;
		DeviceType deviceType = cut.mapToInternal(nullDeviceType);
		assertThat(deviceType).isNull();
	}

	@Test
	public void test_nullDeviceType_mapToExternal() {
		DeviceType nullDeviceType = null;
		cz.siemens.inventory.gen.model.DeviceType actualDeviceType = cut.mapToExternal(nullDeviceType);
		assertThat(actualDeviceType).isNull();
	}

	@Test
	public void test_DeviceType_mapToInternal() {
		cz.siemens.inventory.gen.model.DeviceType expectedDeviceType = getDeviceType(1L, "Dev Type 1", "Me");
		DeviceType actualDeviceType = cut.mapToInternal(expectedDeviceType);
		assertThatDeviceTypesAreEqual(actualDeviceType, expectedDeviceType);
	}

	@Test
	public void test_DeviceType_mapToExternal() {
		DeviceType expectedDeviceType = getDeviceTypeInternal(1L, "Dev Type 1", "Me");
		cz.siemens.inventory.gen.model.DeviceType actualDeviceType = cut.mapToExternal(expectedDeviceType);
		assertThatDeviceTypesAreEqual(actualDeviceType, expectedDeviceType);
	}

	@Test
	public void test_DeviceTypesList_mapToInternal() {
		List<cz.siemens.inventory.gen.model.DeviceType> expectedDeviceTypes = new ArrayList<>();
		expectedDeviceTypes.add(getDeviceType(1L, "Dev Type 1", "Me 1"));
		expectedDeviceTypes.add(getDeviceType(2L, "Dev Type 2", "Me 2"));
		List<DeviceType> actualDeviceTypes = cut.mapToInternal(expectedDeviceTypes);
		assertThatDeviceTypesAreEqual(actualDeviceTypes.get(0), expectedDeviceTypes.get(0));
		assertThatDeviceTypesAreEqual(actualDeviceTypes.get(1), expectedDeviceTypes.get(1));
	}

	private cz.siemens.inventory.gen.model.DeviceType getDeviceType(Long id, String name, String manufacturer) {
		return new cz.siemens.inventory.gen.model.DeviceType().id(id).classification(1).manufacturer(manufacturer).objectTypeName(name)
				.orderNumber("9192737").price(111.10).supplier(new cz.siemens.inventory.gen.model.Supplier().id(2L).name("Supplier"))
				.version("2");
	}

	private DeviceType getDeviceTypeInternal(Long id, String deviceTypeName, String manufacturer) {
		Supplier supplier = new Supplier();
		supplier.setId(2L);
		supplier.setName("Supplier");

		DeviceType deviceType = new DeviceType();
		deviceType.setId(id);
		deviceType.setClassification(1);
		deviceType.setManufacturer(manufacturer);
		deviceType.setObjectTypeName(deviceTypeName);
		deviceType.setOrderNumber("9192737");
		deviceType.setPrice(111.10);
		deviceType.setSupplier(supplier);
		deviceType.setVersion("2");
		return deviceType;
	}

	private void assertThatDeviceTypesAreEqual(DeviceType actualDeviceType, cz.siemens.inventory.gen.model.DeviceType expectedDeviceType) {
		assertThat(actualDeviceType.getId()).isEqualTo(expectedDeviceType.getId());
		assertThat(actualDeviceType.getClassification()).isEqualTo(expectedDeviceType.getClassification());
		assertThat(actualDeviceType.getManufacturer()).isEqualTo(expectedDeviceType.getManufacturer());
		assertThat(actualDeviceType.getObjectTypeName()).isEqualTo(expectedDeviceType.getObjectTypeName());
		assertThat(actualDeviceType.getOrderNumber()).isEqualTo(expectedDeviceType.getOrderNumber());
		assertThat(actualDeviceType.getPrice()).isEqualTo(expectedDeviceType.getPrice());
		assertThat(actualDeviceType.getSupplier().getId()).isEqualTo(expectedDeviceType.getSupplier().getId());
		assertThat(actualDeviceType.getSupplier().getName()).isEqualTo(expectedDeviceType.getSupplier().getName());
		assertThat(actualDeviceType.getVersion()).isEqualTo(expectedDeviceType.getVersion());
	}

	private void assertThatDeviceTypesAreEqual(cz.siemens.inventory.gen.model.DeviceType actualDeviceType, DeviceType expectedDeviceType) {
		assertThat(actualDeviceType.getId()).isEqualTo(expectedDeviceType.getId());
		assertThat(actualDeviceType.getClassification()).isEqualTo(expectedDeviceType.getClassification());
		assertThat(actualDeviceType.getManufacturer()).isEqualTo(expectedDeviceType.getManufacturer());
		assertThat(actualDeviceType.getObjectTypeName()).isEqualTo(expectedDeviceType.getObjectTypeName());
		assertThat(actualDeviceType.getOrderNumber()).isEqualTo(expectedDeviceType.getOrderNumber());
		assertThat(actualDeviceType.getPrice()).isEqualTo(expectedDeviceType.getPrice());
		assertThat(actualDeviceType.getSupplier().getId()).isEqualTo(expectedDeviceType.getSupplier().getId());
		assertThat(actualDeviceType.getSupplier().getName()).isEqualTo(expectedDeviceType.getSupplier().getName());
		assertThat(actualDeviceType.getVersion()).isEqualTo(expectedDeviceType.getVersion());
	}
}
