package cz.siemens.inventory.controllers;


import cz.siemens.inventory.facade.BorrowFacade;
import cz.siemens.inventory.gen.api.BorrowApi;
import cz.siemens.inventory.gen.model.BorrowReturn;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(ApiUris.ROOT_URI)
public class BorrowController extends BaseController implements BorrowApi {

	final static Logger logger = LoggerFactory.getLogger(BorrowController.class);
	private BorrowFacade borrowFacade;

	@Autowired
	public BorrowController(BorrowFacade borrowFacade) {
		this.borrowFacade = borrowFacade;
	}


	@Override
	public ResponseEntity<Void> borrowReturnDevice(@ApiParam(required = true) @Valid @RequestBody BorrowReturn body) {
		logger.info("borrowReturnDevice(deviceId={}) request received", body.getDevice().getId());

		borrowFacade.borrowReturnDevice(body);

		logger.info("borrowReturnDevice(deviceId={}) request finished", body.getDevice().getId());

		return new ResponseEntity<>(HttpStatus.OK);
	}
}