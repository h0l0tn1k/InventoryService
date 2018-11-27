package cz.siemens.inventory.mapper;

import cz.siemens.inventory.entity.Supplier;
import cz.siemens.inventory.mapper.impl.SupplierMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SupplierMapperTest {

	private SupplierMapper cut = new SupplierMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<Supplier> suppliers = cut.mapToInternal(new ArrayList<>());
		assertThat(suppliers).isEmpty();
	}

	@Test
	public void test_nullSupplier_mapToInternal() {
		cz.siemens.inventory.gen.model.Supplier nullSupplier = null;
		Supplier supplier = cut.mapToInternal(nullSupplier);
		assertThat(supplier).isNull();
	}

	@Test
	public void test_Supplier_mapToInternal() {
		cz.siemens.inventory.gen.model.Supplier expectedSupplier = getSupplier(1L, "Supplier 1");
		Supplier actualSupplier = cut.mapToInternal(expectedSupplier);
		assertThatSuppliersAreEqual(actualSupplier, expectedSupplier);
	}

	@Test
	public void test_nullSupplier_mapToExternal() {
		Supplier nullSupplier = null;
		cz.siemens.inventory.gen.model.Supplier supplier = cut.mapToExternal(nullSupplier);
		assertThat(supplier).isNull();
	}

	@Test
	public void test_Supplier_mapToExternal() {
		Supplier expectedSupplier = getSupplierInternal(1L, "Supplier 1");
		cz.siemens.inventory.gen.model.Supplier actualSupplier = cut.mapToExternal(expectedSupplier);
		assertThatSuppliersAreEqual(actualSupplier, expectedSupplier);
	}

	@Test
	public void test_SupplierList_mapToInternal() {
		List<cz.siemens.inventory.gen.model.Supplier> expectedSuppliers = new ArrayList<>();
		expectedSuppliers.add(getSupplier(1L, "Supplier 1"));
		expectedSuppliers.add(getSupplier(2L, "Supplier 2"));
		List<Supplier> actualCompanyOwners = cut.mapToInternal(expectedSuppliers);
		assertThatSuppliersAreEqual(actualCompanyOwners.get(0), expectedSuppliers.get(0));
		assertThatSuppliersAreEqual(actualCompanyOwners.get(1), expectedSuppliers.get(1));
	}

	private cz.siemens.inventory.gen.model.Supplier getSupplier(Long id, String name) {
		return new cz.siemens.inventory.gen.model.Supplier().id(id).name(name);
	}

	private Supplier getSupplierInternal(Long id, String name) {
		Supplier supplier = new Supplier();
		supplier.setId(id);
		supplier.setName(name);
		return supplier;
	}

	private void assertThatSuppliersAreEqual(Supplier actualSupplier, cz.siemens.inventory.gen.model.Supplier expectedSupplier) {
		assertThat(actualSupplier.getId()).isEqualTo(expectedSupplier.getId());
		assertThat(actualSupplier.getName()).isEqualTo(expectedSupplier.getName());
	}

	private void assertThatSuppliersAreEqual(cz.siemens.inventory.gen.model.Supplier actualSupplier, Supplier expectedSupplier) {
		assertThat(actualSupplier.getId()).isEqualTo(expectedSupplier.getId());
		assertThat(actualSupplier.getName()).isEqualTo(expectedSupplier.getName());
	}
}
