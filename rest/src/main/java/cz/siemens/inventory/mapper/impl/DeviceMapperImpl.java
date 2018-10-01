package cz.siemens.inventory.mapper.impl;

import cz.siemens.inventory.entity.Device;
import cz.siemens.inventory.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class DeviceMapperImpl implements DeviceMapper {

	private CompanyOwnerMapper companyOwnerMapper;
	private DepartmentMapper departmentMapper;
	private ProjectMapper projectMapper;
	private DeviceStateMapper deviceStateMapper;
	private DeviceTypeMapper deviceTypeMapper;
	private InventoryRecordMapper inventoryRecordMapper;
	private UserMapper userMapper;
	private DeviceCalibrationMapper deviceCalibrationMapper;
	private DeviceRevisionMapper deviceRevisionMapper;

	@Autowired
	public DeviceMapperImpl(CompanyOwnerMapper companyOwnerMapper, DepartmentMapper departmentMapper,
							ProjectMapper projectMapper, DeviceStateMapper deviceStateMapper,
							DeviceTypeMapper deviceTypeMapper, InventoryRecordMapper inventoryRecordMapper,
							UserMapper userMapper, DeviceCalibrationMapper deviceCalibrationMapper,
							DeviceRevisionMapper deviceRevisionMapper) {
		this.companyOwnerMapper = companyOwnerMapper;
		this.departmentMapper = departmentMapper;
		this.projectMapper = projectMapper;
		this.deviceStateMapper = deviceStateMapper;
		this.deviceTypeMapper = deviceTypeMapper;
		this.inventoryRecordMapper = inventoryRecordMapper;
		this.userMapper = userMapper;
		this.deviceCalibrationMapper = deviceCalibrationMapper;
		this.deviceRevisionMapper = deviceRevisionMapper;
	}

	@Override
	public Device mapToInternal(cz.siemens.inventory.gen.model.Device object) {
		Device result = new Device();
		result.setId(object.getId());
		result.setBarcodeNumber(object.getBarcodeNumber());
		result.setSerialNumber(object.getSerialNumber());
		result.setDefaultLocation(object.getDefaultLocation());
		result.setDeviceCalibration(deviceCalibrationMapper.mapToInternal(object.getCalibration()));
		result.setLastRevision(deviceRevisionMapper.mapToInternal(object.getRevision()));
		result.setComment(object.getComment());
		result.setNstValue(object.getNstValue());
		result.setCompanyOwner(companyOwnerMapper.mapToInternal(object.getCompanyOwner()));
		result.setDepartment(departmentMapper.mapToInternal(object.getDepartment()));
		result.setProject(projectMapper.mapToInternal(object.getProject()));
		result.setDeviceState(deviceStateMapper.mapToInternal(object.getDeviceState()));
		result.setObjectType(deviceTypeMapper.mapToInternal(object.getDeviceType()));
		result.setInventoryRecord(inventoryRecordMapper.mapToInternal(object.getInventoryRecord()));
		result.setAddDate(OffsetDateTime.parse(object.getAddDateString(), DateTimeFormatter.ISO_DATE_TIME));
		result.setHolder(userMapper.mapToInternal(object.getHolder()));
		result.setOwner(userMapper.mapToInternal(object.getOwner()));
		return result;
	}

	@Override
	public cz.siemens.inventory.gen.model.Device mapToExternal(Device object) {
		return new cz.siemens.inventory.gen.model.Device()
				.id(object.getId())
				.serialNumber(object.getSerialNumber())
				.barcodeNumber(object.getBarcodeNumber())
				.defaultLocation(object.getDefaultLocation())
				.calibration(deviceCalibrationMapper.mapToExternal(object.getDeviceCalibration()))
				.revision(deviceRevisionMapper.mapToExternal(object.getLastRevision()))
				.comment(object.getComment())
				.nstValue(object.getNstValue())
				.companyOwner(companyOwnerMapper.mapToExternal(object.getCompanyOwner()))
				.department(departmentMapper.mapToExternal(object.getDepartment()))
				.project(projectMapper.mapToExternal(object.getProject()))
				.deviceState(deviceStateMapper.mapToExternal(object.getDeviceState()))
				.deviceType(deviceTypeMapper.mapToExternal(object.getObjectType()))
				.inventoryRecord(inventoryRecordMapper.mapToExternal(object.getInventoryRecord()))
				.addDateString(object.getAddDate().format(DateTimeFormatter.ISO_DATE_TIME))
				.holder(userMapper.mapToExternal(object.getHolder()))
				.owner(userMapper.mapToExternal(object.getOwner()));
	}
}
