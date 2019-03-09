package cz.siemens.inventory.mapper;

import cz.siemens.inventory.api.gen.model.InventoryState;
import cz.siemens.inventory.api.mapper.InventoryRecordMapper;
import cz.siemens.inventory.entity.InventoryRecord;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class InventoryRecordMapperTest {

	private InventoryRecordMapper cut = new InventoryRecordMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<InventoryRecord> inventoryRecords = cut.mapToInternal(new ArrayList<>());
		assertThat(inventoryRecords).isEmpty();
	}

	@Test
	public void test_nullInventoryRecord_mapToInternal() {
		cz.siemens.inventory.api.gen.model.InventoryRecord nullInventoryRecord = null;
		InventoryRecord inventoryRecord = cut.mapToInternal(nullInventoryRecord);
		assertThat(inventoryRecord).isNull();
	}

	@Test
	public void test_InventoryRecord_mapToInternal() {
		cz.siemens.inventory.api.gen.model.InventoryRecord expectedInventoryRecord = getInventoryRecord(1L, InventoryState.UNCLEAR, "InventoryRecord 1");
		InventoryRecord actualInventoryRecord = cut.mapToInternal(expectedInventoryRecord);
		assertThatInventoryRecordsAreEqual(actualInventoryRecord, expectedInventoryRecord);
	}

	@Test
	public void test_nullInventoryRecord_mapToExternal() {
		InventoryRecord nullInventoryRecord = null;
		cz.siemens.inventory.api.gen.model.InventoryRecord inventoryRecord = cut.mapToExternal(nullInventoryRecord);
		assertThat(inventoryRecord).isNull();
	}

	@Test
	public void test_InventoryRecord_mapToExternal() {
		InventoryRecord expectedInventoryRecord = getInventoryRecordInternal(1L, cz.siemens.inventory.entity.custom.InventoryState.OK, "InventoryRecord 1");
		cz.siemens.inventory.api.gen.model.InventoryRecord actualInventoryRecord = cut.mapToExternal(expectedInventoryRecord);
		assertThatInventoryRecordsAreEqual(actualInventoryRecord, expectedInventoryRecord);
	}

	@Test
	public void test_InventoryRecordList_mapToInternal() {
		List<cz.siemens.inventory.api.gen.model.InventoryRecord> expectedInventoryRecords = new ArrayList<>();
		expectedInventoryRecords.add(getInventoryRecord(1L, InventoryState.FALSE, "InventoryRecord"));
		expectedInventoryRecords.add(getInventoryRecord(2L, InventoryState.OK, "InventoryRecord"));
		List<InventoryRecord> actualCompanyOwners = cut.mapToInternal(expectedInventoryRecords);
		assertThatInventoryRecordsAreEqual(actualCompanyOwners.get(0), expectedInventoryRecords.get(0));
		assertThatInventoryRecordsAreEqual(actualCompanyOwners.get(1), expectedInventoryRecords.get(1));
	}

	private cz.siemens.inventory.api.gen.model.InventoryRecord getInventoryRecord(Long id, InventoryState inventoryState, String comment) {
		return new cz.siemens.inventory.api.gen.model.InventoryRecord().id(id).inventoryState(inventoryState).comment(comment);
	}

	private InventoryRecord getInventoryRecordInternal(Long id, cz.siemens.inventory.entity.custom.InventoryState state, String comment) {
		InventoryRecord inventoryRecord = new InventoryRecord();
		inventoryRecord.setId(id);
		inventoryRecord.setRegistered(state);
		inventoryRecord.setComment(comment);
		return inventoryRecord;
	}

	private void assertThatInventoryRecordsAreEqual(InventoryRecord actualInventoryRecord, cz.siemens.inventory.api.gen.model.InventoryRecord expectedInventoryRecord) {
		assertThat(actualInventoryRecord.getId()).isEqualTo(expectedInventoryRecord.getId());
		assertThat(actualInventoryRecord.getRegistered().toString()).isEqualTo(expectedInventoryRecord.getInventoryState().toString());
		assertThat(actualInventoryRecord.getComment()).isEqualTo(expectedInventoryRecord.getComment());
	}

	private void assertThatInventoryRecordsAreEqual(cz.siemens.inventory.api.gen.model.InventoryRecord actualInventoryRecord, InventoryRecord expectedInventoryRecord) {
		assertThat(actualInventoryRecord.getId()).isEqualTo(expectedInventoryRecord.getId());
		assertThat(actualInventoryRecord.getInventoryState().toString()).isEqualTo(expectedInventoryRecord.getRegistered().toString());
		assertThat(actualInventoryRecord.getComment()).isEqualTo(expectedInventoryRecord.getComment());
	}
}
