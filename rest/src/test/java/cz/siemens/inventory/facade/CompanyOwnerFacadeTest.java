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

public class CompanyOwnerFacadeTest {

	private CompanyOwnerFacade cut;
	private cz.siemens.inventory.gen.model.CompanyOwner expectedCompanyOwnerExternal = getExternalCompanyOwner(1L,"Company Owner");

	@Before
	public void setup() {
		CompanyOwnerDao companyOwnerDaoMock = Mockito.mock(CompanyOwnerDao.class);
		CompanyOwnerMapper companyOwnerMapperMock = new CompanyOwnerMapperImpl();
		cut = new CompanyOwnerFacadeImpl(companyOwnerDaoMock, companyOwnerMapperMock);
		CompanyOwner companyOwnerInternal = new CompanyOwner();
		companyOwnerInternal.setId(expectedCompanyOwnerExternal.getId());
		companyOwnerInternal.setName(expectedCompanyOwnerExternal.getName());

		doReturn(Optional.of(companyOwnerInternal)).when(companyOwnerDaoMock).findById(1L);
		doReturn(new ArrayList<CompanyOwner>()).when(companyOwnerDaoMock).findAll();
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

	private cz.siemens.inventory.gen.model.CompanyOwner getExternalCompanyOwner(Long id, String name) {
		return new cz.siemens.inventory.gen.model.CompanyOwner().id(id).name(name);
	}
}
