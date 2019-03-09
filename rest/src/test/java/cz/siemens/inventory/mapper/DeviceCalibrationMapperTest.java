package cz.siemens.inventory.mapper;

import cz.siemens.inventory.api.mapper.DeviceCalibrationMapper;
import cz.siemens.inventory.entity.ApplianceCalibration;
import cz.siemens.inventory.api.gen.model.DeviceCalibration;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static cz.siemens.inventory.api.mapper.DateFormat.YYYY_MM_DD;
import static org.assertj.core.api.Assertions.assertThat;

public class DeviceCalibrationMapperTest {

	private DeviceCalibrationMapper cut = new DeviceCalibrationMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<ApplianceCalibration> deviceCalibrations = cut.mapToInternal(new ArrayList<>());
		assertThat(deviceCalibrations).isEmpty();
	}

	@Test
	public void test_nullDeviceCalibration_mapToInternal() {
		DeviceCalibration nullDeviceCalibration = null;
		ApplianceCalibration deviceCalibration = cut.mapToInternal(nullDeviceCalibration);
		assertThat(deviceCalibration).isNull();
	}

	@Test
	public void test_DeviceCalibration_mapToInternal() {
		DeviceCalibration expectedDeviceCalibration = getDeviceCalibration(1L, 4);
		ApplianceCalibration actualDeviceCalibration = cut.mapToInternal(expectedDeviceCalibration);
		assertThatDeviceCalibrationsAreEqual(actualDeviceCalibration, expectedDeviceCalibration);
	}

	@Test
	public void test_DeviceCalibration_withIncorrectDate_mapToInternal() {
		DeviceCalibration expectedDeviceCalibration = getDeviceCalibration(1L, 4);
		expectedDeviceCalibration.setLastCalibrationDateString("289hfhw2d");
		ApplianceCalibration actualApplianceCalibration = cut.mapToInternal(expectedDeviceCalibration);
		expectedDeviceCalibration.setLastCalibrationDateString(null);
		assertThatDeviceCalibrationsAreEqual(actualApplianceCalibration, expectedDeviceCalibration);
	}

	@Test
	public void test_DeviceRevision_withNullDate_mapToInternal() {
		DeviceCalibration expectedDeviceCalibration = getDeviceCalibration(1L, 4);
		expectedDeviceCalibration.setLastCalibrationDateString(null);
		ApplianceCalibration actualApplianceCalibration = cut.mapToInternal(expectedDeviceCalibration);

		assertThatDeviceCalibrationsAreEqual(actualApplianceCalibration, expectedDeviceCalibration);
	}

	@Test
	public void test_nullDeviceCalibration_mapToExternal() {
		ApplianceCalibration nullDeviceCalibration = null;
		DeviceCalibration deviceCalibration = cut.mapToExternal(nullDeviceCalibration);
		assertThat(deviceCalibration).isNull();
	}

	@Test
	public void test_DeviceCalibration_mapToExternal() {
		ApplianceCalibration expectedDeviceCalibration = getDeviceCalibrationInternal(1L, 2);
		DeviceCalibration actualDeviceCalibration = cut.mapToExternal(expectedDeviceCalibration);
		assertThatDeviceCalibrationsAreEqual(actualDeviceCalibration, expectedDeviceCalibration);
	}

	@Test
	public void test_CompanyOwnerList_mapToInternal() {
		List<DeviceCalibration> expectedDeviceCalibrations = new ArrayList<>();
		expectedDeviceCalibrations.add(getDeviceCalibration(1L, 2));
		expectedDeviceCalibrations.add(getDeviceCalibration(2L, 1));
		List<ApplianceCalibration> actualCompanyOwners = cut.mapToInternal(expectedDeviceCalibrations);
		assertThatDeviceCalibrationsAreEqual(actualCompanyOwners.get(0), expectedDeviceCalibrations.get(0));
		assertThatDeviceCalibrationsAreEqual(actualCompanyOwners.get(1), expectedDeviceCalibrations.get(1));
	}

	private DeviceCalibration getDeviceCalibration(Long id, int interval) {
		return new DeviceCalibration().id(id).calibrationInterval(interval).lastCalibrationDateString(LocalDate.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
	}

	private ApplianceCalibration getDeviceCalibrationInternal(Long id, int interval) {
		ApplianceCalibration deviceCalibration = new ApplianceCalibration();
		deviceCalibration.setId(id);
		deviceCalibration.setInterval(interval);
		deviceCalibration.setLastCalibration(LocalDate.now());
		return deviceCalibration;
	}

	private void assertThatDeviceCalibrationsAreEqual(ApplianceCalibration actualDeviceCalibration, DeviceCalibration expectedDeviceCalibration) {
		assertThat(actualDeviceCalibration.getId()).isEqualTo(expectedDeviceCalibration.getId());
		assertThat(actualDeviceCalibration.getInterval()).isEqualTo(expectedDeviceCalibration.getCalibrationInterval());
		if (actualDeviceCalibration.getLastCalibration() == null) {
			assertThat(expectedDeviceCalibration.getLastCalibrationDateString()).isEqualTo(null);
		} else {
			assertThat(actualDeviceCalibration.getLastCalibration().format(DateTimeFormatter.ofPattern(YYYY_MM_DD))).isEqualTo(expectedDeviceCalibration.getLastCalibrationDateString());
		}
	}

	private void assertThatDeviceCalibrationsAreEqual(DeviceCalibration actualDeviceCalibration, ApplianceCalibration expectedDeviceCalibration) {
		assertThat(actualDeviceCalibration.getId()).isEqualTo(expectedDeviceCalibration.getId());
		assertThat(actualDeviceCalibration.getCalibrationInterval()).isEqualTo(expectedDeviceCalibration.getInterval());
		assertThat(actualDeviceCalibration.getLastCalibrationDateString()).isEqualTo(expectedDeviceCalibration.getLastCalibration().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));

	}
}
