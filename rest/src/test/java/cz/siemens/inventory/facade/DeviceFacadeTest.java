package cz.siemens.inventory.facade;

import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.InventoryRecord;
import cz.siemens.inventory.exception.BadRequestException;
import cz.siemens.inventory.exception.ConflictException;
import cz.siemens.inventory.facade.impl.DeviceFacadeImpl;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.mapper.DeviceMapper;
import cz.siemens.inventory.mapper.impl.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static cz.siemens.inventory.mapper.DateFormat.YYYY_MM_DD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class DeviceFacadeTest {

	private DeviceFacade cut;
	private DeviceDao deviceDao;
	private DeviceMapper deviceMapper;
	private AuditLogFacade auditLogFacade;
	private DeviceInternal deviceInternal;

	@Before
	public void setup() {
		deviceDao = Mockito.mock(DeviceDao.class);
		auditLogFacade = Mockito.mock(AuditLogFacade.class);
		deviceMapper = new DeviceMapperImpl(new CompanyOwnerMapperImpl(), new DepartmentMapperImpl(), new ProjectMapperImpl(),
				new DeviceStateMapperImpl(), new DeviceTypeMapperImpl(new SupplierMapperImpl()), new InventoryRecordMapperImpl(),
				new UserMapperImpl(), new DeviceCalibrationMapperImpl(), new DeviceRevisionMapperImpl());
		cut = new DeviceFacadeImpl(deviceDao, deviceMapper, auditLogFacade);
		deviceInternal = new DeviceInternal();
		deviceInternal.setId(1L);
		deviceInternal.setSerialNumber("SN123");
		deviceInternal.setBarcodeNumber("85893048573");

		doReturn(Optional.of(deviceInternal)).when(deviceDao).findById(1L);
		doReturn(new ArrayList<DeviceInternal>()).when(deviceDao).findAll();
	}

	@Test
	public void getDevices_returnsList() {
		List<cz.siemens.inventory.gen.model.Device> Devices = cut.getDevices();

		assertThat(Devices).isEmpty();
	}

//	@Test
//	public void getDevice_byId_returnsDevice() {
//		Optional<cz.siemens.inventory.gen.model.Device> optionalDevice = cut.getDevice(1L);
//
//		assertThat(optionalDevice.isPresent()).isTrue();
//		assertThat(deviceMapper.mapToInternal(optionalDevice.get())).isEqualTo(Optional.of(deviceInternal));
//	}

	@Test(expected = BadRequestException.class)
	public void createDevice_withAddDateInTheFuture_throwsBadRequest() {

		Device deviceToCreate = new Device()
				.addDateString(OffsetDateTime.now().plusDays(5).format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
		cut.createDevice(deviceToCreate);
	}

	@Test(expected = BadRequestException.class)
	public void createDevice_withEmptySerialNumber_throwsBadRequest() {

		Device deviceToCreate = new Device()
				.addDateString(OffsetDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(YYYY_MM_DD)))
				.serialNumber("");
		cut.createDevice(deviceToCreate);
	}

	@Test(expected = BadRequestException.class)
	public void createDevice_withBlankSerialNumber_throwsBadRequest() {

		Device deviceToCreate = new Device()
				.addDateString(OffsetDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(YYYY_MM_DD)))
				.serialNumber("	 ");
		cut.createDevice(deviceToCreate);
	}

	@Test(expected = ConflictException.class)
	public void createDevice_withExistingSerialNumber_throwsConflictException() {

		doReturn(Optional.of(deviceInternal)).when(deviceDao).getDeviceBySerialNumber(deviceInternal.getSerialNumber());

		Device deviceToCreate = new Device()
				.addDateString(OffsetDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(YYYY_MM_DD)))
				.serialNumber(deviceInternal.getSerialNumber());
		cut.createDevice(deviceToCreate);
	}

	@Test(expected = ConflictException.class)
	public void createDevice_withExistingBarcodeNumber_throwsConflictException() {

		doReturn(Optional.of(deviceInternal)).when(deviceDao).getDeviceByBarcodeNumber(deviceInternal.getBarcodeNumber());

		Device deviceToCreate = new Device()
				.addDateString(OffsetDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern(YYYY_MM_DD)))
				.serialNumber(deviceInternal.getSerialNumber())
				.barcodeNumber(deviceInternal.getBarcodeNumber());
		cut.createDevice(deviceToCreate);
	}

//	@Test
//	public void updateDevice_updatesDevice() {
//
//		doReturn(deviceInternal).when(deviceDao).save(deviceInternal);
//
//		cz.siemens.inventory.gen.model.Device updatedDevice = cut.updateDevice(
//				deviceMapper.mapToExternal(deviceInternal));
//
//		verify(deviceDao).save(deviceInternal);
//
//		assertThat(updatedDevice).isEqualTo(deviceMapper.mapToExternal(deviceInternal));
//	}

	@Test
	public void deleteDevice_deletesDevice() {
		Long id = 3L;
		cut.deleteDevice(id);

		verify(deviceDao).deleteById(id);
	}
}
