package cz.siemens.inventory.rest.controllers;

import cz.siemens.inventory.dao.GenericDao;
import cz.siemens.inventory.entity.CompanyOwner;
import cz.siemens.inventory.rest.ApiUris;
import cz.siemens.inventory.rest.exceptions.ResourceAlreadyExistsException;
import cz.siemens.inventory.rest.exceptions.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUris.ROOT_URI_COMPANYOWNERS)
public class CompanyOwnersController {

    final static Logger logger = LoggerFactory.getLogger(CompanyOwnersController.class);

    @Autowired
    private GenericDao<CompanyOwner> companyOwnerDao;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CompanyOwner> findAllCompanyOwners(){
        logger.debug("rest findAllCompanyOwners() called");
        return companyOwnerDao.readAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompanyOwner findById(@PathVariable("id") Long id) throws Exception {
        logger.debug("rest findById({id}) called", id);

        try {
            return companyOwnerDao.read(id);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
/*
    @RequestMapping(value="/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompanyOwner findByName(@PathVariable("name") String name) throws Exception {
        logger.debug("rest findByName({id}) called", name);

        //TODO: fix encoding
        try {
            return companyOwnerDao.findByName(name);
        } catch(Exception ex) {
            throw new ResourceNotFoundException();
        }
    }*/

    @RequestMapping(value = "/create", method= RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public final CompanyOwner createCompanyOwner(@RequestBody CompanyOwner companyOwner) throws Exception {
        logger.debug("rest createMachine({0}) called", companyOwner.toString());

        try {
            companyOwnerDao.create(companyOwner);
            return companyOwner;
        } catch(Exception ex) {
            throw new ResourceAlreadyExistsException();
        }
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void remove(@PathVariable("id") Long id) throws Exception {
        logger.debug("rest remove({id}) called", id);

        try {
            companyOwnerDao.delete(companyOwnerDao.read(id));
        } catch (Exception ex) {
            throw new ResourceNotFoundException();
        }
    }
}
