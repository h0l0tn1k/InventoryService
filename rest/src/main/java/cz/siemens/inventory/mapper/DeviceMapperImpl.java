package cz.siemens.inventory.mapper;

import cz.siemens.inventory.api.mapper.*;
import cz.siemens.inventory.entity.DeviceInternal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static cz.siemens.inventory.api.mapper.DateFormat.YYYY_MM_DD;
import static cz.siemens.inventory.api.mapper.DateFormat.formatter;

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
	public DeviceInternal mapToInternal(cz.siemens.inventory.api.gen.model.Device object) {
		if(object == null) {
			return null;
		}
		DeviceInternal result = new DeviceInternal();
		result.setId(object.getId());
		result.setBarcodeNumber(object.getBarcodeNumber());
		result.setSerialNumber(object.getSerialNumber());
		result.setDefaultLocation(object.getDefaultLocation());
		result.setDeviceCalibration(deviceCalibrationMapper.mapToInternal(object.getCalibration()));
		result.setLastRevision(deviceRevisionMapper.mapToInternal(object.getRevision()));
		result.setComment(object.getComment());
		result.setNstValue(object.getNstValue());
		result.setInventoryNumber(object.getInventoryNumber());
		result.setCompanyOwner(companyOwnerMapper.mapToInternal(object.getCompanyOwner()));
		result.setDepartment(departmentMapper.mapToInternal(object.getDepartment()));
		result.setProject(projectMapper.mapToInternal(object.getProject()));
		result.setDeviceState(deviceStateMapper.mapToInternal(object.getDeviceState()));
		result.setObjectType(deviceTypeMapper.mapToInternal(object.getDeviceType()));
		result.setInventoryRecord(inventoryRecordMapper.mapToInternal(object.getInventoryRecord()));
		result.setAddDate(parseStringToDate(object.getAddDateString()));
		result.setHolder(userMapper.mapToInternal(object.getHolder()));
		result.setOwner(userMapper.mapToInternal(object.getOwner()));
		return result;
	}

	@Override
	public cz.siemens.inventory.api.gen.model.Device mapToExternal(DeviceInternal object) {
		if(object == null) {
			return null;
		}
		String addDateString = (object.getAddDate() == null)
				? "" : object.getAddDate().format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
		return new cz.siemens.inventory.api.gen.model.Device()
				.id(object.getId())
				.serialNumber(object.getSerialNumber())
				.barcodeNumber(object.getBarcodeNumber())
				.defaultLocation(object.getDefaultLocation())
				.calibration(deviceCalibrationMapper.mapToExternal(object.getDeviceCalibration()))
				.revision(deviceRevisionMapper.mapToExternal(object.getLastRevision()))
				.comment(object.getComment())
				.nstValue(object.getNstValue())
				.inventoryNumber(object.getInventoryNumber())
				.companyOwner(companyOwnerMapper.mapToExternal(object.getCompanyOwner()))
				.department(departmentMapper.mapToExternal(object.getDepartment()))
				.project(projectMapper.mapToExternal(object.getProject()))
				.deviceState(deviceStateMapper.mapToExternal(object.getDeviceState()))
				.deviceType(deviceTypeMapper.mapToExternal(object.getObjectType()))
				.inventoryRecord(inventoryRecordMapper.mapToExternal(object.getInventoryRecord()))
				.addDateString(addDateString)
				.holder(userMapper.mapToExternal(object.getHolder()))
				.owner(userMapper.mapToExternal(object.getOwner()));
	}

	private OffsetDateTime parseStringToDate(String dateString) {
		if (StringUtils.isBlank(dateString)) {
			return null;
		}
		try {
			ZonedDateTime zonedDateTime = LocalDate.parse(dateString, formatter).atStartOfDay().atZone(ZoneId.of("Europe/Berlin"));
			return zonedDateTime.toOffsetDateTime();
		} catch (DateTimeParseException e) {
			return null;
		}
	}
}
