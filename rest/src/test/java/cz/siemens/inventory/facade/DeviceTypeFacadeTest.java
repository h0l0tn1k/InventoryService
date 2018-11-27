package cz.siemens.inventory.facade;

import cz.siemens.inventory.dao.DeviceTypeDao;
import cz.siemens.inventory.entity.DeviceType;
import cz.siemens.inventory.entity.Supplier;
import cz.siemens.inventory.facade.impl.DeviceTypeFacadeImpl;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.mapper.DeviceTypeMapper;
import cz.siemens.inventory.mapper.impl.DeviceTypeMapperImpl;
import cz.siemens.inventory.mapper.impl.SupplierMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class DeviceTypeFacadeTest {

	private DeviceTypeFacade cut;
	private DeviceTypeDao deviceTypeDao;
	private DeviceTypeMapper deviceTypeMapper;
	private DeviceType deviceTypeInternal;

	@Before
	public void setup() {
		deviceTypeDao = Mockito.mock(DeviceTypeDao.class);
		deviceTypeMapper = new DeviceTypeMapperImpl(new SupplierMapperImpl());
		cut = new DeviceTypeFacadeImpl(deviceTypeDao, deviceTypeMapper);
		deviceTypeInternal = new DeviceType();
		deviceTypeInternal.setId(4L);
		deviceTypeInternal.setObjectTypeName("Type Name");
		deviceTypeInternal.setVersion("23");
		deviceTypeInternal.setSupplier(new Supplier());
		deviceTypeInternal.setPrice(234.0);
		deviceTypeInternal.setOrderNumber("197471238");
		deviceTypeInternal.setManufacturer("Manufacturer");
		deviceTypeInternal.setClassification(3);

		doReturn(Optional.of(deviceTypeInternal)).when(deviceTypeDao).findById(1L);
		doReturn(new ArrayList<DeviceType>()).when(deviceTypeDao).findAll();
	}

	@Test
	public void getDeviceTypes_returnsList() {
		List<cz.siemens.inventory.gen.model.DeviceType> DeviceTypes = cut.getDeviceTypes();

		assertThat(DeviceTypes).isEmpty();
	}

	@Test
	public void getDeviceType_byId_returnsDeviceType() {
		Optional<cz.siemens.inventory.gen.model.DeviceType> optionalDeviceType = cut.getDeviceType(1L);

		assertThat(optionalDeviceType.isPresent()).isTrue();
		assertThat(optionalDeviceType).isEqualTo(Optional.of(deviceTypeMapper.mapToExternal(deviceTypeInternal)));
	}

	@Test
	public void createDeviceType_createsDeviceType() {

		doReturn(deviceTypeInternal).when(deviceTypeDao).save(deviceTypeInternal);

		cz.siemens.inventory.gen.model.DeviceType createdDeviceType = cut.createDeviceType(deviceTypeMapper.mapToExternal(deviceTypeInternal));

		assertThat(createdDeviceType).isNotNull();
	}

	@Test
	public void updateDeviceType_updatesDeviceType() {

		doReturn(deviceTypeInternal).when(deviceTypeDao).save(deviceTypeInternal);

		cz.siemens.inventory.gen.model.DeviceType updatedDeviceType = cut.updateDeviceType(
				deviceTypeMapper.mapToExternal(deviceTypeInternal));

		verify(deviceTypeDao).save(deviceTypeInternal);

		assertThat(updatedDeviceType).isEqualTo(deviceTypeMapper.mapToExternal(deviceTypeInternal));
	}

	@Test
	public void deleteDeviceType_deletesDeviceType() {
		Long id = 3L;
		cut.deleteDeviceType(id);

		verify(deviceTypeDao).deleteById(id);
	}

	@Test
	public void getDeviceTypesByName_returnsDeviceTypes() {
		List<DeviceType> deviceTypes = new ArrayList<>();
		deviceTypes.add(deviceTypeInternal);
		deviceTypes.add(deviceTypeInternal);
		deviceTypes.add(deviceTypeInternal);

		doReturn(deviceTypes).when(deviceTypeDao).getDeviceTypesByObjectTypeNameContaining("name");

		List<cz.siemens.inventory.gen.model.DeviceType> deviceTypesByName = cut.getDeviceTypesByName("name");

		assertThat(deviceTypesByName).hasSize(3);
		assertThat(deviceTypesByName.get(0).getId()).isEqualTo(deviceTypeInternal.getId());
	}
}
