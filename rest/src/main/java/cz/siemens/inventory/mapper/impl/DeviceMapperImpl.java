package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.Device;
import cz.siemens.inventory.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeviceMapperImpl implements DeviceMapper {

	private CompanyOwnerMapper companyOwnerMapper;
	private DepartmentMapper departmentMapper;
	private ProjectMapper projectMapper;
	private DeviceStateMapper deviceStateMapper;
	private DeviceTypeMapper deviceTypeMapper;

	@Autowired
	public DeviceMapperImpl(CompanyOwnerMapper companyOwnerMapper, DepartmentMapper departmentMapper,
							ProjectMapper projectMapper, DeviceStateMapper deviceStateMapper,
							DeviceTypeMapper deviceTypeMapper) {
		this.companyOwnerMapper = companyOwnerMapper;
		this.departmentMapper = departmentMapper;
		this.projectMapper = projectMapper;
		this.deviceStateMapper = deviceStateMapper;
		this.deviceTypeMapper = deviceTypeMapper;
	}

	@Override
	public Device mapToInternal(cz.siemens.inventory.gen.model.Device object) {
		Device result = new Device();
		result.setId(object.getId());
		result.setBarcodeNumber(object.getBarcodeNumber());
		result.setSerialNumber(object.getSerialNumber());
		result.setDefaultLocation(object.getDefaultLocation());
		result.setComment(object.getComment());
		result.setCompanyOwner(companyOwnerMapper.mapToInternal(object.getCompanyOwner()));
		result.setDepartment(departmentMapper.mapToInternal(object.getDepartment()));
		result.setProject(projectMapper.mapToInternal(object.getProject()));
		result.setDeviceState(deviceStateMapper.mapToInternal(object.getDeviceState()));
		result.setObjectType(deviceTypeMapper.mapToInternal(object.getDeviceType()));
		result.setAddDate(object.getAddDate());
		return result;
	}

	@Override
	public cz.siemens.inventory.gen.model.Device mapToExternal(Device object) {
		return new cz.siemens.inventory.gen.model.Device()
				.id(object.getId())
				.serialNumber(object.getSerialNumber())
				.barcodeNumber(object.getBarcodeNumber())
				.defaultLocation(object.getDefaultLocation())
				.comment(object.getComment())
				.companyOwner(companyOwnerMapper.mapToExternal(object.getCompanyOwner()))
				.department(departmentMapper.mapToExternal(object.getDepartment()))
				.project(projectMapper.mapToExternal(object.getProject()))
				.deviceState(deviceStateMapper.mapToExternal(object.getDeviceState()))
				.deviceType(deviceTypeMapper.mapToExternal(object.getObjectType()))
				.addDate(object.getAddDate());
	}
}
