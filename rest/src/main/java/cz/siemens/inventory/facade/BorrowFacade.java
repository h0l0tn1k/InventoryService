package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.BorrowReturn;

public interface BorrowFacade {

	void borrowReturnDevice(BorrowReturn borrowReturn);
}
