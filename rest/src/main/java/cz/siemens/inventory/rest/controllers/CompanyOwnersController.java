package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.CompanyOwnerDao;
import cz.siemens.inventory.entity.CompanyOwner;
import cz.siemens.inventory.rest.ApiUris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_COMPANYOWNERS)
public class CompanyOwnersController {

    final static Logger logger = LoggerFactory.getLogger(CompanyOwnersController.class);

    @Autowired
    private CompanyOwnerDao companyOwnerDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CompanyOwner> findAllCompanyOwners(){
        logger.debug("rest findAllCompanyOwners() called");
        return companyOwnerDao.findAll();
    }
}
