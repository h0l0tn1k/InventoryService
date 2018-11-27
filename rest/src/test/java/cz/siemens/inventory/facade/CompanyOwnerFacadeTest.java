package cz.siemens.inventory.facade;

import cz.siemens.inventory.dao.CompanyOwnerDao;
import cz.siemens.inventory.entity.CompanyOwner;
import cz.siemens.inventory.facade.impl.CompanyOwnerFacadeImpl;
import cz.siemens.inventory.mapper.CompanyOwnerMapper;
import cz.siemens.inventory.mapper.impl.CompanyOwnerMapperImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

public class CompanyOwnerFacadeTest {

	private CompanyOwnerFacade cut;
	private CompanyOwnerDao companyOwnerDao;
	private CompanyOwnerMapper companyOwnerMapper;
	private cz.siemens.inventory.gen.model.CompanyOwner expectedCompanyOwnerExternal = getExternalCompanyOwner(1L,"Company Owner");

	@Before
	public void setup() {
		companyOwnerDao = Mockito.mock(CompanyOwnerDao.class);
		companyOwnerMapper = new CompanyOwnerMapperImpl();
		cut = new CompanyOwnerFacadeImpl(companyOwnerDao, companyOwnerMapper);
		CompanyOwner companyOwnerInternal = new CompanyOwner();
		companyOwnerInternal.setId(expectedCompanyOwnerExternal.getId());
		companyOwnerInternal.setName(expectedCompanyOwnerExternal.getName());

		doReturn(Optional.of(companyOwnerInternal)).when(companyOwnerDao).findById(1L);
		doReturn(new ArrayList<CompanyOwner>()).when(companyOwnerDao).findAll();
	}

	@Test
	public void getCompanyOwners_returnsList() {
		List<cz.siemens.inventory.gen.model.CompanyOwner> companyOwners = cut.getCompanyOwners();

		assertThat(companyOwners).isEmpty();
	}

	@Test
	public void getCompanyOwner_byId_returnsOwner() {
		Optional<cz.siemens.inventory.gen.model.CompanyOwner> optionalCompanyOwner = cut.getCompanyOwner(1L);

		assertThat(optionalCompanyOwner.isPresent()).isTrue();
		assertThat(optionalCompanyOwner).isEqualTo(Optional.of(expectedCompanyOwnerExternal));
	}

	@Test
	public void createCompanyOwner_createsOwner() {
		CompanyOwner expectedCompanyOwner = new CompanyOwner();
		expectedCompanyOwner.setId(4L);
		expectedCompanyOwner.setName("Company");

		doReturn(expectedCompanyOwner).when(companyOwnerDao).save(expectedCompanyOwner);

		cz.siemens.inventory.gen.model.CompanyOwner createdCompanyOwner = cut.createCompanyOwner(companyOwnerMapper.mapToExternal(expectedCompanyOwner));

		assertThat(createdCompanyOwner).isNotNull();
	}

	@Test
	public void updateCompanyOwner_updatesOwner() {
		CompanyOwner expectedCompanyOwner = new CompanyOwner();
		expectedCompanyOwner.setId(4L);
		expectedCompanyOwner.setName("Company");

		doReturn(expectedCompanyOwner).when(companyOwnerDao).save(expectedCompanyOwner);

		cz.siemens.inventory.gen.model.CompanyOwner updatedCompanyOwner = cut.updateCompanyOwner(
				companyOwnerMapper.mapToExternal(expectedCompanyOwner));

		verify(companyOwnerDao).save(expectedCompanyOwner);

		assertThat(updatedCompanyOwner).isEqualTo(companyOwnerMapper.mapToExternal(expectedCompanyOwner));
	}

	@Test
	public void deleteCompanyOwner_deletesOwner() {
		Long id = 3L;
		cut.deleteCompanyOwner(id);

		verify(companyOwnerDao).deleteById(id);
	}

	private cz.siemens.inventory.gen.model.CompanyOwner getExternalCompanyOwner(Long id, String name) {
		return new cz.siemens.inventory.gen.model.CompanyOwner().id(id).name(name);
	}
}
