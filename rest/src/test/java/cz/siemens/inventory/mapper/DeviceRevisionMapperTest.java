package cz.siemens.inventory.mapper;

import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.gen.model.DeviceRevision;
import cz.siemens.inventory.mapper.impl.DeviceRevisionMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static cz.siemens.inventory.mapper.DateFormat.YYYY_MM_DD;
import static org.assertj.core.api.Assertions.assertThat;

public class DeviceRevisionMapperTest {

	private DeviceRevisionMapper cut = new DeviceRevisionMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<ApplianceRevision> deviceRevisions = cut.mapToInternal(new ArrayList<>());
		assertThat(deviceRevisions).isEmpty();
	}

	@Test
	public void test_nullDeviceRevision_mapToInternal() {
		DeviceRevision nullDeviceRevision = null;
		ApplianceRevision deviceRevision = cut.mapToInternal(nullDeviceRevision);
		assertThat(deviceRevision).isNull();
	}

	@Test
	public void test_DeviceRevision_mapToInternal() {
		DeviceRevision expectedDeviceRevision = getDeviceRevision(1L, 4);
		ApplianceRevision actualDeviceRevision = cut.mapToInternal(expectedDeviceRevision);
		assertThatDeviceRevisionsAreEqual(actualDeviceRevision, expectedDeviceRevision);
	}

	@Test
	public void test_DeviceRevision_withIncorrectDate_mapToInternal() {
		DeviceRevision expectedDeviceRevision = getDeviceRevision(1L, 4);
		expectedDeviceRevision.setLastRevisionDateString("289hfhw2d");
		ApplianceRevision actualDeviceRevision = cut.mapToInternal(expectedDeviceRevision);
		expectedDeviceRevision.setLastRevisionDateString(null);
		assertThatDeviceRevisionsAreEqual(actualDeviceRevision, expectedDeviceRevision);
	}

	@Test
	public void test_DeviceRevision_withNullDate_mapToInternal() {
		DeviceRevision expectedDeviceRevision = getDeviceRevision(1L, 4);
		expectedDeviceRevision.setLastRevisionDateString(null);
		ApplianceRevision actualDeviceRevision = cut.mapToInternal(expectedDeviceRevision);

		assertThatDeviceRevisionsAreEqual(actualDeviceRevision, expectedDeviceRevision);
	}

	@Test
	public void test_nullDeviceRevision_mapToExternal() {
		ApplianceRevision nullDeviceRevision = null;
		DeviceRevision deviceRevision = cut.mapToExternal(nullDeviceRevision);
		assertThat(deviceRevision).isNull();
	}

	@Test
	public void test_DeviceRevision_mapToExternal() {
		ApplianceRevision expectedDeviceRevision = getDeviceRevisionInternal(1L, 2);
		DeviceRevision actualDeviceRevision = cut.mapToExternal(expectedDeviceRevision);
		assertThatDeviceRevisionsAreEqual(actualDeviceRevision, expectedDeviceRevision);
	}

	@Test
	public void test_CompanyOwnerList_mapToInternal() {
		List<DeviceRevision> expectedDeviceRevisions = new ArrayList<>();
		expectedDeviceRevisions.add(getDeviceRevision(1L, 2));
		expectedDeviceRevisions.add(getDeviceRevision(2L, 1));
		List<ApplianceRevision> actualCompanyOwners = cut.mapToInternal(expectedDeviceRevisions);
		assertThatDeviceRevisionsAreEqual(actualCompanyOwners.get(0), expectedDeviceRevisions.get(0));
		assertThatDeviceRevisionsAreEqual(actualCompanyOwners.get(1), expectedDeviceRevisions.get(1));
	}

	private DeviceRevision getDeviceRevision(Long id, int interval) {
		return new DeviceRevision().id(id).revisionInterval(interval).lastRevisionDateString(LocalDate.now().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));
	}

	private ApplianceRevision getDeviceRevisionInternal(Long id, int interval) {
		ApplianceRevision DeviceRevision = new ApplianceRevision();
		DeviceRevision.setId(id);
		DeviceRevision.setInterval(interval);
		DeviceRevision.setLastRevision(LocalDate.now());
		return DeviceRevision;
	}

	private void assertThatDeviceRevisionsAreEqual(ApplianceRevision actualDeviceRevision, DeviceRevision expectedDeviceRevision) {
		assertThat(actualDeviceRevision.getId()).isEqualTo(expectedDeviceRevision.getId());
		assertThat(actualDeviceRevision.getInterval()).isEqualTo(expectedDeviceRevision.getRevisionInterval());
		if (actualDeviceRevision.getLastRevision() == null) {
			assertThat(expectedDeviceRevision.getLastRevisionDateString()).isEqualTo(null);
		} else {
			assertThat(actualDeviceRevision.getLastRevision().format(DateTimeFormatter.ofPattern(YYYY_MM_DD))).isEqualTo(expectedDeviceRevision.getLastRevisionDateString());
		}
	}

	private void assertThatDeviceRevisionsAreEqual(DeviceRevision actualDeviceRevision, ApplianceRevision expectedDeviceRevision) {
		assertThat(actualDeviceRevision.getId()).isEqualTo(expectedDeviceRevision.getId());
		assertThat(actualDeviceRevision.getRevisionInterval()).isEqualTo(expectedDeviceRevision.getInterval());
		assertThat(actualDeviceRevision.getLastRevisionDateString()).isEqualTo(expectedDeviceRevision.getLastRevision().format(DateTimeFormatter.ofPattern(YYYY_MM_DD)));

	}
}
