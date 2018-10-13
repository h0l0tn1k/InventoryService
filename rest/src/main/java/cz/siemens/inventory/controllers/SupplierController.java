package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.SupplierFacade;
import cz.siemens.inventory.gen.api.SuppliersApi;
import cz.siemens.inventory.gen.model.Supplier;
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
public class SupplierController extends BaseController implements SuppliersApi {

    final static Logger logger = LoggerFactory.getLogger(SupplierController.class);
    private SupplierFacade supplierFacade;

    @Autowired
    public SupplierController(SupplierFacade supplierFacade) {
        this.supplierFacade = supplierFacade;
    }

    @Override
    public ResponseEntity<List<Supplier>> getSuppliers() {
        logger.info("getSuppliers request received");

        ResponseEntity<List<Supplier>> result = ResponseEntity.ok(supplierFacade.getSuppliers());

        logger.info("getSuppliers request finished");

        return result;
    }

    @Override
    public ResponseEntity<Supplier> getSupplier(@PathVariable("supplierId") Long supplierId) {
        logger.info("getSupplier({}) request received", supplierId);

        ResponseEntity<Supplier> result = returnOptional(supplierFacade.getSupplier(supplierId));

        logger.info("getSupplier({}) request finished", supplierId);

        return result;
    }

    @Override
    public ResponseEntity<Supplier> createSupplier(@ApiParam(required = true) @Valid @RequestBody Supplier body) {
        logger.info("createSupplier({}) request received", body.toString());

        Supplier createdSupplier = supplierFacade.createSupplier(body);

        logger.info("createSupplier({}) request finished", createdSupplier.getId());

        return returnCreatedResponse(createdSupplier, createdSupplier.getId().toString());
    }

    @Override
    public ResponseEntity<Supplier> updateSupplier(@ApiParam(required = true) @PathVariable("supplierId") Long supplierId,
                                                   @ApiParam(required = true) @Valid @RequestBody Supplier body) {
        logger.info("updateSupplier({}, {}) request received", supplierId, body.toString());

        Supplier updatedSupplier = supplierFacade.updateSupplier(body);

        logger.info("updateSupplier({}, {}) request finished", supplierId, updatedSupplier.toString());

        return ResponseEntity.ok(updatedSupplier);
    }

    @Override
    public ResponseEntity<Void> deleteSupplier(@ApiParam(required = true) @PathVariable("supplierId") Long supplierId) {
        logger.info("deleteSupplier({}) request received", supplierId);

        supplierFacade.deleteSupplier(supplierId);

        logger.info("deleteSupplier({}) request finished", supplierId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}