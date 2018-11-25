package cz.siemens.inventory.controllers.mapper;

import cz.siemens.inventory.entity.CompanyOwner;
import cz.siemens.inventory.mapper.CompanyOwnerMapper;
import cz.siemens.inventory.mapper.impl.CompanyOwnerMapperImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CompanyOwnerMapperTest {

	private CompanyOwnerMapper cut = new CompanyOwnerMapperImpl();

	@Before
	public void setup() {

	}

	@Test
	public void test_emptyList_mapToInternal() {
		List<CompanyOwner> companyOwners = cut.mapToInternal(new ArrayList<>());
		assertThat(companyOwners).isEmpty();
	}

	@Test
	public void test_nullCompanyOwner_mapToInternal() {
		cz.siemens.inventory.gen.model.CompanyOwner nullCompanyOwner = null;
		CompanyOwner companyOwner = cut.mapToInternal(nullCompanyOwner);
		assertThat(companyOwner).isNull();
	}

	@Test
	public void test_CompanyOwner_mapToInternal() {
		cz.siemens.inventory.gen.model.CompanyOwner expectedCompanyOwner = getCompanyOwner(1L, "Company owner");
		CompanyOwner actualCompanyOwner = cut.mapToInternal(expectedCompanyOwner);
		assertThatCompanyOwnersAreEqual(actualCompanyOwner, expectedCompanyOwner);
	}

	@Test
	public void test_CompanyOwnerList_mapToInternal() {
		List<cz.siemens.inventory.gen.model.CompanyOwner> expectedCompanyOwners = new ArrayList<>();
		expectedCompanyOwners.add(getCompanyOwner(1L, "Company owner 1"));
		expectedCompanyOwners.add(getCompanyOwner(2L, "Company owner 2"));
		List<CompanyOwner> actualCompanyOwners = cut.mapToInternal(expectedCompanyOwners);
		assertThatCompanyOwnersAreEqual(actualCompanyOwners.get(0), expectedCompanyOwners.get(0));
		assertThatCompanyOwnersAreEqual(actualCompanyOwners.get(1), expectedCompanyOwners.get(1));
	}

	private cz.siemens.inventory.gen.model.CompanyOwner getCompanyOwner(Long id, String name) {
		return new cz.siemens.inventory.gen.model.CompanyOwner().id(id).name(name);
	}

	private void assertThatCompanyOwnersAreEqual(CompanyOwner actualCompanyOwner, cz.siemens.inventory.gen.model.CompanyOwner expectedCompanyOwner) {
		assertThat(actualCompanyOwner.getId()).isEqualTo(expectedCompanyOwner.getId());
		assertThat(actualCompanyOwner.getName()).isEqualTo(expectedCompanyOwner.getName());

	}
}
