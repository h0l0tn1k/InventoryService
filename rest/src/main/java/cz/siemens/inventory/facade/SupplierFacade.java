package cz.siemens.inventory.facade;

import cz.siemens.inventory.gen.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface SupplierFacade {

	List<Supplier> getSuppliers();

	Optional<Supplier> getSupplier(long supplierId);

	Supplier createSupplier(Supplier supplier);

	Supplier updateSupplier(Supplier supplier);

	void deleteSupplier(long supplierId);
}
