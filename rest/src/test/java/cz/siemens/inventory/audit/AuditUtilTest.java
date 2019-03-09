package cz.siemens.inventory.audit;

import cz.siemens.inventory.audit.AuditUtils.AuditUtil;
import cz.siemens.inventory.entity.*;
import cz.siemens.inventory.entity.custom.InventoryState;
import org.junit.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cz.siemens.inventory.api.mapper.DateFormat.YYYY_MM_DD;
import static org.assertj.core.api.Assertions.assertThat;

public class AuditUtilTest {

	@Test
	public void getDeviceAuditEntries_withNoChange_returnsEmptyList() {
		DeviceInternal deviceInternal = getDevice();

		List<String> actualRecordEntries = AuditUtil.getDeviceAuditEntries(deviceInternal, deviceInternal);
		assertThat(actualRecordEntries).isEmpty();
	}

	@Test
	public void getDeviceAuditEntries_withNullPreviousDevice_returnsDeviceCreated() {

		List<String> actualRecordEntries = AuditUtil.getDeviceAuditEntries(null, getDevice());

		assertThat(actualRecordEntries).hasSize(1);
		assertThat(actualRecordEntries.get(0)).startsWith("Device created: ");
	}

	@Test
	public void getDeviceAuditEntries_removingValues_returnsRemovedEntries() {
		DeviceInternal previousDevice = getDevice();
		previousDevice.setComment("Some comment");
		previousDevice.setNstValue("123");
		previousDevice.setInventoryNumber("333");
		previousDevice.setBarcodeNumber("111333");
		previousDevice.setSerialNumber("23498");
		previousDevice.setDefaultLocation("locc");

		List<String> actualRecordEntries = AuditUtil.getDeviceAuditEntries(previousDevice, getDevice());

		assertThat(actualRecordEntries).hasSize(6);
		assertThat(actualRecordEntries.get(0)).isEqualTo("Device QR code removed");
		assertThat(actualRecordEntries.get(1)).isEqualTo("Device serial number removed");
		assertThat(actualRecordEntries.get(2)).isEqualTo("Device inventory number removed");
		assertThat(actualRecordEntries.get(3)).isEqualTo("Device default location removed");
		assertThat(actualRecordEntries.get(4)).isEqualTo("Device NST value removed");
		assertThat(actualRecordEntries.get(5)).isEqualTo("Device comment removed");
	}

	@Test
	public void getDeviceAuditEntries_withDifferentProperties_returnsEntries() {
		DeviceInternal previousDevice = getDevice();
		previousDevice.setAddDate(OffsetDateTime.now().minusDays(2));
		DeviceInternal newDevice = getDevice();
		newDevice.setInventoryNumber("111");
		newDevice.setComment("Comm");
		newDevice.setNstValue("666");
		newDevice.setDefaultLocation("777");
		newDevice.setSerialNumber("888");
		newDevice.setBarcodeNumber("999");

		LoginUserScd owner = new LoginUserScd();
		owner.setFirstName("First");
		owner.setLastName("Tester");
		newDevice.setOwner(owner);

		Department department = new Department();
		department.setName("Dept");
		newDevice.setDepartment(department);

		CompanyOwner companyOwner = new CompanyOwner();
		companyOwner.setName("Company");
		newDevice.setCompanyOwner(companyOwner);

		DeviceType deviceType = new DeviceType();
		deviceType.setId(2L);
		deviceType.setObjectTypeName("New Dev Type");
		newDevice.setObjectType(deviceType);

		Project project = new Project();
		project.setName("Proj");
		newDevice.setProject(project);

		OffsetDateTime time = OffsetDateTime.now();
		newDevice.setAddDate(time);

		DeviceState deviceState = new DeviceState();
		deviceState.setName("State");
		newDevice.setDeviceState(deviceState);

		List<String> actualRecordEntries = AuditUtil.getDeviceAuditEntries(previousDevice, newDevice);

		assertThat(actualRecordEntries).hasSize(13);
		assertThat(actualRecordEntries.get(0)).isEqualTo("Device QR code changed to 999");
		assertThat(actualRecordEntries.get(1)).isEqualTo("Device type changed to New Dev Type");
		assertThat(actualRecordEntries.get(2)).isEqualTo("Device serial number changed to 888");
		assertThat(actualRecordEntries.get(3)).isEqualTo("Device inventory number changed to 111");
		assertThat(actualRecordEntries.get(4)).isEqualTo("Device owner name changed to First Tester");
		assertThat(actualRecordEntries.get(5)).isEqualTo("Device default location changed to 777");
		assertThat(actualRecordEntries.get(6)).isEqualTo("Device department changed to Dept");
		assertThat(actualRecordEntries.get(7)).isEqualTo("Device company owner changed to Company");
		assertThat(actualRecordEntries.get(8)).isEqualTo("Device project name changed to Proj");
		assertThat(actualRecordEntries.get(9)).isEqualTo("Device NST value changed to 666");
		assertThat(actualRecordEntries.get(10)).isEqualTo("Device add date changed to " + time.format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
		assertThat(actualRecordEntries.get(11)).isEqualTo("Device status changed to State");
		assertThat(actualRecordEntries.get(12)).isEqualTo("Device comment changed to Comm");
	}

	@Test
	public void getInventoryRecordAuditEntries_withNoChange_returnsEmptyList() {

		InventoryRecord record = new InventoryRecord();
		record.setRegistered(InventoryState.OK);

		List<String> inventoryRecordAuditEntries = AuditUtil.getInventoryRecordAuditEntries(record, record);

		assertThat(inventoryRecordAuditEntries).isEmpty();
	}

	@Test
	public void getInventoryRecordAuditEntries_withDifferentProperties_returnsEntries() {

		InventoryRecord oldRecord = new InventoryRecord();
		oldRecord.setRegistered(InventoryState.False);

		InventoryRecord newRecord = new InventoryRecord();
		newRecord.setRegistered(InventoryState.OK);
		newRecord.setComment("New Comment");

		List<String> inventoryRecordAuditEntries = AuditUtil.getInventoryRecordAuditEntries(oldRecord, newRecord);

		assertThat(inventoryRecordAuditEntries).hasSize(2);
		assertThat(inventoryRecordAuditEntries.get(0)).isEqualTo("Inventory result changed to OK");
		assertThat(inventoryRecordAuditEntries.get(1)).isEqualTo("Inventory comment changed to New Comment");
	}

	@Test
	public void getRevisionAuditEntries_withNoChange_returnsEmptyList() {

		ApplianceRevision applianceRevision = new ApplianceRevision();
		applianceRevision.setInterval(3);

		List<String> revisionAuditEntries = AuditUtil.getRevisionAuditEntries(applianceRevision, applianceRevision);

		assertThat(revisionAuditEntries).isEmpty();
	}

	@Test
	public void getRevisionAuditEntries_withDifferentProperties_returnsEntries() {

		ApplianceRevision applianceRevision = new ApplianceRevision();
		applianceRevision.setInterval(2);

		ApplianceRevision newApplianceRevision = new ApplianceRevision();
		newApplianceRevision.setInterval(4);
		LocalDate time = LocalDate.now();
		newApplianceRevision.setLastRevision(time);

		List<String> revisionAuditEntries = AuditUtil.getRevisionAuditEntries(applianceRevision, newApplianceRevision);

		assertThat(revisionAuditEntries).hasSize(2);
		assertThat(revisionAuditEntries.get(0)).isEqualTo("Electric revision interval changed to 4");
		assertThat(revisionAuditEntries.get(1)).isEqualTo("Electric revision performed on " + time.format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
	}

	@Test
	public void getCalibrationAuditEntries_withNoChange_returnsEmptyList() {

		ApplianceCalibration applianceCalibration = new ApplianceCalibration();
		applianceCalibration.setInterval(4);

		List<String> calibrationAuditEntries = AuditUtil.getCalibrationAuditEntries(applianceCalibration, applianceCalibration);

		assertThat(calibrationAuditEntries).isEmpty();
	}

	@Test
	public void getCalibrationAuditEntries_withDifferentProperties_returnsEntries() {

		ApplianceCalibration applianceCalibration = new ApplianceCalibration();
		applianceCalibration.setInterval(1);

		ApplianceCalibration newApplianceCalibration = new ApplianceCalibration();
		newApplianceCalibration.setInterval(4);
		LocalDate time = LocalDate.now();
		newApplianceCalibration.setLastCalibration(time);

		List<String> revisionAuditEntries = AuditUtil.getCalibrationAuditEntries(applianceCalibration, newApplianceCalibration);

		assertThat(revisionAuditEntries).hasSize(2);
		assertThat(revisionAuditEntries.get(0)).isEqualTo("Calibration interval changed to 4");
		assertThat(revisionAuditEntries.get(1)).isEqualTo("Calibration performed on " + time.format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
	}

	private DeviceInternal getDevice() {
		DeviceInternal deviceInternal = new DeviceInternal();
		deviceInternal.setId(3L);
		deviceInternal.setObjectType(new DeviceType());
		deviceInternal.setOwner(new LoginUserScd());
		deviceInternal.setDepartment(new Department());
		deviceInternal.setCompanyOwner(new CompanyOwner());
		deviceInternal.setInventoryRecord(new InventoryRecord());
		deviceInternal.setDeviceCalibration(new ApplianceCalibration());
		deviceInternal.setLastRevision(new ApplianceRevision());
		deviceInternal.setAddDate(OffsetDateTime.now());
		deviceInternal.setHolder(new LoginUserScd());
		deviceInternal.setDeviceState(new DeviceState());
		deviceInternal.setProject(new Project());
		return deviceInternal;
	}
}
