package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.CompanyOwnerFacade;
import cz.siemens.inventory.gen.api.CompanyOwnersApi;
import cz.siemens.inventory.gen.model.CompanyOwner;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class CompanyOwnerController extends BaseController implements CompanyOwnersApi {

	final static Logger logger = LoggerFactory.getLogger(CompanyOwnerController.class);
	private CompanyOwnerFacade companyOwnerFacade;

	@Autowired
	public CompanyOwnerController(CompanyOwnerFacade companyOwnerFacade) {
		this.companyOwnerFacade = companyOwnerFacade;
	}

	@Override
	public ResponseEntity<List<CompanyOwner>> getCompanyOwners() {
		logger.info("getCompanyOwners request received");

		ResponseEntity<List<CompanyOwner>> result = ResponseEntity.ok(companyOwnerFacade.getCompanyOwners());
		logger.info("getCompanyOwners request finished");

		return result;
	}

	@Override
	public ResponseEntity<CompanyOwner> getCompanyOwner(@PathVariable("companyOwnerId") Long companyOwnerId) {
		logger.info("getCompanyOwner({}) request received", companyOwnerId);

		ResponseEntity<CompanyOwner> result = returnOptional(companyOwnerFacade.getCompanyOwner(companyOwnerId));

		logger.info("getCompanyOwner({}) request finished", companyOwnerId);

		return result;
	}

	@Override
	public ResponseEntity<CompanyOwner> createCompanyOwner(@ApiParam(required = true) @Valid @RequestBody CompanyOwner body) {
		logger.info("createCompanyOwner({}) request received", body.toString());

		CompanyOwner createdCompanyOwner = companyOwnerFacade.createCompanyOwner(body);

		logger.info("createCompanyOwner({}) request finished", createdCompanyOwner.getId());

		return returnCreatedResponse(createdCompanyOwner, createdCompanyOwner.getId().toString());
	}

	@Override
	public ResponseEntity<Void> deleteCompanyOwner(@ApiParam(required = true) @PathVariable("companyOwnerId") Long companyOwnerId) {
		logger.info("deleteCompanyOwner({}) request received", companyOwnerId);

		companyOwnerFacade.deleteCompanyOwner(companyOwnerId);

		logger.info("deleteCompanyOwner({}) request finished", companyOwnerId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}