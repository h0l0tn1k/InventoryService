package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.DeviceType;
import cz.siemens.inventory.mapper.DeviceTypeMapper;
import cz.siemens.inventory.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceTypeMapperImpl implements DeviceTypeMapper {

	private SupplierMapper supplierMapper;

	@Autowired
	public DeviceTypeMapperImpl(SupplierMapper supplierMapper) {
		this.supplierMapper = supplierMapper;
	}

	@Override
	public DeviceType mapToInternal(cz.siemens.inventory.gen.model.DeviceType object) {
		DeviceType deviceType = new DeviceType();
		deviceType.setId(object.getId());
		deviceType.setClassification(object.getClassification());
		deviceType.setManufacturer(object.getManufacturer());
		deviceType.setObjectTypeName(object.getObjectTypeName());
		deviceType.setOrderNumber(object.getOrderNumber());
		deviceType.setPrice(object.getPrice());
		deviceType.setVersion(object.getVersion());
		deviceType.setSupplier(supplierMapper.mapToInternal(object.getSupplier()));
		return deviceType;
	}

	@Override
	public cz.siemens.inventory.gen.model.DeviceType mapToExternal(DeviceType object) {
		cz.siemens.inventory.gen.model.DeviceType deviceType = new cz.siemens.inventory.gen.model.DeviceType();
		return deviceType.id(object.getId()).classification(object.getClassification()).manufacturer(object.getManufacturer())
				.objectTypeName(object.getObjectTypeName()).orderNumber(object.getOrderNumber()).price(object.getPrice())
				.version(object.getVersion()).supplier(supplierMapper.mapToExternal(object.getSupplier()));
	}
}