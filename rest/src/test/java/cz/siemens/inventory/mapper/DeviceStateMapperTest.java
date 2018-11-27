package cz.siemens.inventory.mapper;

import cz.siemens.inventory.entity.DeviceState;
import cz.siemens.inventory.mapper.impl.DeviceStateMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeviceStateMapperTest {

	private DeviceStateMapper cut = new DeviceStateMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<DeviceState> deviceStates = cut.mapToInternal(new ArrayList<>());
		assertThat(deviceStates).isEmpty();
	}

	@Test
	public void test_nullDeviceState_mapToInternal() {
		cz.siemens.inventory.gen.model.DeviceState nullDeviceState = null;
		DeviceState deviceState = cut.mapToInternal(nullDeviceState);
		assertThat(deviceState).isNull();
	}

	@Test
	public void test_DeviceState_mapToInternal() {
		cz.siemens.inventory.gen.model.DeviceState expectedDeviceState = getDeviceState(1L, "DeviceState 1");
		DeviceState actualDeviceState = cut.mapToInternal(expectedDeviceState);
		assertThatDeviceStatesAreEqual(actualDeviceState, expectedDeviceState);
	}

	@Test
	public void test_nullDeviceState_mapToExternal() {
		DeviceState nullDeviceState = null;
		cz.siemens.inventory.gen.model.DeviceState deviceState = cut.mapToExternal(nullDeviceState);
		assertThat(deviceState).isNull();
	}

	@Test
	public void test_DeviceState_mapToExternal() {
		DeviceState expectedDeviceState = getDeviceStateInternal(1L, "DeviceState 1");
		cz.siemens.inventory.gen.model.DeviceState actualDeviceState = cut.mapToExternal(expectedDeviceState);
		assertThatDeviceStatesAreEqual(actualDeviceState, expectedDeviceState);
	}

	@Test
	public void test_DeviceStateList_mapToInternal() {
		List<cz.siemens.inventory.gen.model.DeviceState> expectedDeviceStates = new ArrayList<>();
		expectedDeviceStates.add(getDeviceState(1L, "DeviceState 1"));
		expectedDeviceStates.add(getDeviceState(2L, "DeviceState 2"));
		List<DeviceState> actualCompanyOwners = cut.mapToInternal(expectedDeviceStates);
		assertThatDeviceStatesAreEqual(actualCompanyOwners.get(0), expectedDeviceStates.get(0));
		assertThatDeviceStatesAreEqual(actualCompanyOwners.get(1), expectedDeviceStates.get(1));
	}

	private cz.siemens.inventory.gen.model.DeviceState getDeviceState(Long id, String name) {
		return new cz.siemens.inventory.gen.model.DeviceState().id(id).name(name);
	}

	private DeviceState getDeviceStateInternal(Long id, String name) {
		DeviceState deviceState = new DeviceState();
		deviceState.setId(id);
		deviceState.setName(name);
		return deviceState;
	}

	private void assertThatDeviceStatesAreEqual(DeviceState actualDeviceState, cz.siemens.inventory.gen.model.DeviceState expectedDeviceState) {
		assertThat(actualDeviceState.getId()).isEqualTo(expectedDeviceState.getId());
		assertThat(actualDeviceState.getName()).isEqualTo(expectedDeviceState.getName());
	}

	private void assertThatDeviceStatesAreEqual(cz.siemens.inventory.gen.model.DeviceState actualDeviceState, DeviceState expectedDeviceState) {
		assertThat(actualDeviceState.getId()).isEqualTo(expectedDeviceState.getId());
		assertThat(actualDeviceState.getName()).isEqualTo(expectedDeviceState.getName());
	}
}
