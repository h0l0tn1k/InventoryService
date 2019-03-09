package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.SupplierFacade;
import cz.siemens.inventory.api.mapper.SupplierMapper;
import cz.siemens.inventory.dao.SupplierDao;
import cz.siemens.inventory.entity.Supplier;
import cz.siemens.inventory.mapper.SupplierMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class SupplierFacadeTest {

	private SupplierFacade cut;
	private SupplierDao supplierDao;
	private SupplierMapper supplierMapper;
	private Supplier supplierInternal;

	@Before
	public void setup() {
		supplierDao = Mockito.mock(SupplierDao.class);
		supplierMapper = new SupplierMapperImpl();
		cut = new SupplierFacadeImpl(supplierDao, supplierMapper);
		supplierInternal = new Supplier();
		supplierInternal.setId(4L);
		supplierInternal.setName("Supplier");

		doReturn(Optional.of(supplierInternal)).when(supplierDao).findById(1L);
		doReturn(new ArrayList<Supplier>()).when(supplierDao).findAll();
	}

	@Test
	public void getSuppliers_returnsList() {
		List<cz.siemens.inventory.api.gen.model.Supplier> Suppliers = cut.getSuppliers();

		assertThat(Suppliers).isEmpty();
	}

	@Test
	public void getSupplier_byId_returnsSupplier() {
		Optional<cz.siemens.inventory.api.gen.model.Supplier> optionalSupplier = cut.getSupplier(1L);

		assertThat(optionalSupplier.isPresent()).isTrue();
		assertThat(optionalSupplier).isEqualTo(Optional.of(supplierMapper.mapToExternal(supplierInternal)));
	}

	@Test
	public void createSupplier_createsSupplier() {

		doReturn(supplierInternal).when(supplierDao).save(supplierInternal);

		cz.siemens.inventory.api.gen.model.Supplier createdSupplier = cut.createSupplier(supplierMapper.mapToExternal(supplierInternal));

		assertThat(createdSupplier).isNotNull();
	}

	@Test
	public void updateSupplier_updatesSupplier() {

		doReturn(supplierInternal).when(supplierDao).save(supplierInternal);

		cz.siemens.inventory.api.gen.model.Supplier updatedSupplier = cut.updateSupplier(
				supplierMapper.mapToExternal(supplierInternal));

		verify(supplierDao).save(supplierInternal);

		assertThat(updatedSupplier).isEqualTo(supplierMapper.mapToExternal(supplierInternal));
	}

	@Test
	public void deleteSupplier_deletesSupplier() {
		Long id = 3L;
		cut.deleteSupplier(id);

		verify(supplierDao).deleteById(id);
	}
}
