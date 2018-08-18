package cz.siemens.inventory.controllers;

import cz.siemens.inventory.InventoryService;
import cz.siemens.inventory.controllers.utils.TestUtil;
import cz.siemens.inventory.facade.*;
import cz.siemens.inventory.gen.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = InventoryService.class)
public class DeviceControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected DeviceFacade deviceFacade;

	@Autowired
	protected SupplierFacade supplierFacade;

	@Autowired
	protected DeviceTypeFacade deviceTypeFacade;

	@Autowired
	protected CompanyOwnerFacade companyOwnerFacade;

	@Autowired
	protected DepartmentFacade departmentFacade;

	@Autowired
	protected ProjectFacade projectFacade;

	@Autowired
	protected DeviceStateFacade deviceStateFacade;

	@Autowired
	protected InventoryRecordFacade inventoryRecordFacade;

	protected Device device;


	@Before
	public void setup() {
		Supplier supplier = new Supplier().name("Test Supplier");
		supplier = supplierFacade.createSupplier(supplier);

		DeviceType deviceType = new DeviceType().objectTypeName("TestType").classification(1).manufacturer("Test").orderNumber("0").version("1").price(9999.0).supplier(supplier);
		deviceType = deviceTypeFacade.createDeviceType(deviceType);

		CompanyOwner companyOwner = new CompanyOwner().name("Test Company Owner");
		companyOwner = companyOwnerFacade.createCompanyOwner(companyOwner);

		Department department = new Department().name("Test Department");
		department = departmentFacade.createDepartment(department);

		Project project = new Project().name("Test Project");
		project = projectFacade.createProject(project);

		DeviceState deviceState = new DeviceState().name("good");
		deviceState = deviceStateFacade.createDeviceState(deviceState);

		InventoryRecord inventoryRecord = new InventoryRecord().inventoryState(InventoryState.OK);
		//inventoryRecord = inventoryRecordFacade.createInventoryRecord(inventoryRecord);

		device = new Device().deviceType(deviceType).serialNumber("85441115").barcodeNumber("87878s7d9")
				.companyOwner(companyOwner).department(department).project(project)
				.defaultLocation("Test").deviceState(deviceState).inventoryRecord(inventoryRecord);

		deviceFacade.createDevice(device);
	}

	@Test
	public void getDevices() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get(ApiUris.ROOT_URI + "/devices")).andReturn();
		List<Device> devices = TestUtil.mapResponseToList(mvcResult, Device.class);
	}

	@Test
	public void getDevice() {
	}

	@Test
	public void createDevice() {
	}

	@Test
	public void deleteDevice() {
	}

	@Test
	public void getDeviceWithBarcodeNumber() {
	}

	@Test
	public void getDeviceWithSerialNumber() {
	}

	@Test
	public void getDevicesWithSerialNumberLike() {
	}

	@Test
	public void getDevicesBorrowedBy() {
	}

	@Test
	public void updateDeviceHolder() {
	}
}