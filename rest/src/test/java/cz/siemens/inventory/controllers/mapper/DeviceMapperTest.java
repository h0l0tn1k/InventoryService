package cz.siemens.inventory.controllers.mapper;

import cz.siemens.inventory.entity.*;
import cz.siemens.inventory.entity.CompanyOwner;
import cz.siemens.inventory.entity.Department;
import cz.siemens.inventory.entity.DeviceState;
import cz.siemens.inventory.entity.DeviceType;
import cz.siemens.inventory.entity.InventoryRecord;
import cz.siemens.inventory.entity.Supplier;
import cz.siemens.inventory.gen.model.*;
import cz.siemens.inventory.mapper.DeviceMapper;
import cz.siemens.inventory.mapper.impl.*;
import javafx.util.converter.DateStringConverter;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static cz.siemens.inventory.mapper.DateFormat.YYYY_MM_DD;
import static org.assertj.core.api.Assertions.assertThat;

public class DeviceMapperTest {

	private DeviceMapper cut = new DeviceMapperImpl(new CompanyOwnerMapperImpl(), new DepartmentMapperImpl(), new ProjectMapperImpl(),
			new DeviceStateMapperImpl(), new DeviceTypeMapperImpl(new SupplierMapperImpl()), new InventoryRecordMapperImpl(),
			new UserMapperImpl(), new DeviceCalibrationMapperImpl(), new DeviceRevisionMapperImpl());

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<DeviceInternal> devices = cut.mapToInternal(new ArrayList<>());
		assertThat(devices).isEmpty();
	}

	@Test
	public void test_nullDevice_mapToInternal() {
		Device nullDevice = null;
		DeviceInternal deviceInternal = cut.mapToInternal(nullDevice);
		assertThat(deviceInternal).isNull();
	}

	@Test
	public void test_nullDevice_mapToExternal() {
		DeviceInternal nullDevice = null;
		Device actualDevice = cut.mapToExternal(nullDevice);
		assertThat(actualDevice).isNull();
	}

	@Test
	public void test_Device_mapToInternal() {
		Device expectedDevice = getDevice(1L);
		DeviceInternal actualDevice = cut.mapToInternal(expectedDevice);
		assertThatDevicesAreEqual(actualDevice, expectedDevice);
	}

	@Test
	public void test_Device_mapToExternal() {
		DeviceInternal expectedDevice = getDeviceInternal(1L);
		Device actualDevice = cut.mapToExternal(expectedDevice);
		assertThatDevicesAreEqual(actualDevice, expectedDevice);
	}

	@Test
	public void test_DevicesList_mapToInternal() {
		List<Device> expectedDevices = new ArrayList<>();
		expectedDevices.add(getDevice(1L));
		expectedDevices.add(getDevice(2L));
		List<DeviceInternal> actualDevices = cut.mapToInternal(expectedDevices);
		assertThatDevicesAreEqual(actualDevices.get(0), expectedDevices.get(0));
		assertThatDevicesAreEqual(actualDevices.get(1), expectedDevices.get(1));
	}

	private Device getDevice(Long id) {
		User user = new User().flagAdmin(true).flagBorrow(true).flagInventory(true).flagRevision(true).flagWrite(true).flagRead(true);
		return new Device().id(id).barcodeNumber("123").serialNumber("321").comment("test").defaultLocation("def")
				.nstValue("111").inventoryNumber("3").deviceState(new cz.siemens.inventory.gen.model.DeviceState())
				.holder(user).addDateString(OffsetDateTime.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)))
				.revision(new DeviceRevision()).calibration(new DeviceCalibration()).inventoryRecord(new cz.siemens.inventory.gen.model.InventoryRecord().inventoryState(InventoryState.FALSE))
				.companyOwner(new cz.siemens.inventory.gen.model.CompanyOwner()).department(new cz.siemens.inventory.gen.model.Department())
				.owner(user).deviceType(new cz.siemens.inventory.gen.model.DeviceType());
	}

	private DeviceInternal getDeviceInternal(Long id) {
		Supplier supplier = new Supplier();
		supplier.setId(2L);
		supplier.setName("Supplier");

		DeviceInternal device = new DeviceInternal();
		device.setId(id);
		device.setBarcodeNumber("123");
		device.setSerialNumber("321");
		device.setComment("test");
		device.setDefaultLocation("def");
		device.setNstValue("111");
		device.setInventoryNumber("3");
		device.setDeviceState(new DeviceState());
		device.setHolder(new LoginUserScd());
		device.setAddDate(OffsetDateTime.now());
		device.setLastRevision(new ApplianceRevision());
		device.setDeviceCalibration(new ApplianceCalibration());


		InventoryRecord inventoryRecord = new InventoryRecord();
		inventoryRecord.setRegistered(cz.siemens.inventory.entity.custom.InventoryState.OK);
		device.setInventoryRecord(inventoryRecord);
		device.setCompanyOwner(new CompanyOwner());
		device.setDepartment(new Department());
		device.setOwner(new LoginUserScd());
		device.setObjectType(new DeviceType());
		return device;
	}

	private void assertThatDevicesAreEqual(DeviceInternal actualDevice, Device expectedDevice) {
		assertThat(actualDevice.getId()).isEqualTo(expectedDevice.getId());
		assertThat(actualDevice.getSerialNumber()).isEqualTo(expectedDevice.getSerialNumber());
		assertThat(actualDevice.getBarcodeNumber()).isEqualTo(expectedDevice.getBarcodeNumber());
		assertThat(actualDevice.getInventoryNumber()).isEqualTo(expectedDevice.getInventoryNumber());
		assertThat(actualDevice.getDefaultLocation()).isEqualTo(expectedDevice.getDefaultLocation());
		assertThat(actualDevice.getComment()).isEqualTo(expectedDevice.getComment());
		assertThat(actualDevice.getNstValue()).isEqualTo(expectedDevice.getNstValue());
		// other complex entities
	}

	private void assertThatDevicesAreEqual(Device actualDevice, DeviceInternal expectedDevice) {
		assertThat(actualDevice.getId()).isEqualTo(expectedDevice.getId());
		assertThat(actualDevice.getSerialNumber()).isEqualTo(expectedDevice.getSerialNumber());
		assertThat(actualDevice.getBarcodeNumber()).isEqualTo(expectedDevice.getBarcodeNumber());
		assertThat(actualDevice.getInventoryNumber()).isEqualTo(expectedDevice.getInventoryNumber());
		assertThat(actualDevice.getDefaultLocation()).isEqualTo(expectedDevice.getDefaultLocation());
		assertThat(actualDevice.getComment()).isEqualTo(expectedDevice.getComment());
		assertThat(actualDevice.getNstValue()).isEqualTo(expectedDevice.getNstValue());
		// other complex entities
	}
}
