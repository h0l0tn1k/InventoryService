package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.api.gen.model.BorrowReturn;

public interface BorrowFacade {

	void borrowReturnDevice(BorrowReturn borrowReturn);
}
