package cz.siemens.inventory.audit.AuditUtils;

import cz.siemens.inventory.entity.ApplianceCalibration;
import cz.siemens.inventory.entity.ApplianceRevision;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.InventoryRecord;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static cz.siemens.inventory.mapper.DateFormat.YYYY_MM_DD;

public class AuditUtil {

	public static List<String> getDeviceAuditEntries(DeviceInternal previousVersion, DeviceInternal newVersion) {
		List<String> auditEntries = new ArrayList<>();

		if (previousVersion == null) {
			auditEntries.add("Device created: " + newVersion.toAuditLogString());
			return auditEntries;
		}

		addAuditEntryIfChanged(previousVersion.getObjectTypeName(), newVersion.getObjectTypeName(), "Device type", auditEntries);
		addAuditEntryIfChanged(previousVersion.getBarcodeNumber(), newVersion.getBarcodeNumber(), "Device QR code", auditEntries);
		addAuditEntryIfChanged(previousVersion.getSerialNumber(), newVersion.getSerialNumber(), "Device serial number", auditEntries);
		addAuditEntryIfChanged(previousVersion.getInventoryNumber(), newVersion.getInventoryNumber(), "Device inventory number", auditEntries);
		addAuditEntryIfChanged(previousVersion.getOwnerName(), newVersion.getOwnerName(), "Device owner name", auditEntries);
		addAuditEntryIfChanged(previousVersion.getDefaultLocation(), newVersion.getDefaultLocation(), "Device default location", auditEntries);
		addAuditEntryIfChanged(previousVersion.getDepartmentName(), newVersion.getDepartmentName(), "Device department", auditEntries);
		addAuditEntryIfChanged(previousVersion.getCompanyOwnerName(), newVersion.getCompanyOwnerName(), "Device company owner", auditEntries);
		addAuditEntryIfChanged(previousVersion.getProjectName(), newVersion.getProjectName(), "Device project name", auditEntries);
		addAuditEntryIfChanged(previousVersion.getNstValue(), newVersion.getNstValue(), "Device NST value", auditEntries);
		addAuditEntryIfChanged(getAddDateString(previousVersion.getAddDate()), getAddDateString(newVersion.getAddDate()), "Device add date", auditEntries);
		addAuditEntryIfChanged(previousVersion.getDeviceStateName(), newVersion.getDeviceStateName(), "Device status", auditEntries);
		addAuditEntryIfChanged(previousVersion.getComment(), newVersion.getComment(), "Device comment", auditEntries);

		return auditEntries;
	}

	public static List<String> getInventoryRecordAuditEntries(InventoryRecord previousVersion, InventoryRecord newVersion) {
		List<String> auditEntries = new ArrayList<>();

		addAuditEntryIfChanged(previousVersion.getRegistered().toString(), newVersion.getRegistered().toString(), "Inventory result", auditEntries);
		addAuditEntryIfChanged(previousVersion.getComment(), newVersion.getComment(), "Inventory comment", auditEntries);

		return auditEntries;
	}

	public static List<String> getRevisionAuditEntries(ApplianceRevision previousVersion, ApplianceRevision newVersion) {
		List<String> auditEntries = new ArrayList<>();

		addAuditEntryIfChanged(previousVersion.getInterval().toString(), newVersion.getInterval().toString(), "Electric revision interval", auditEntries);

		String lastRevisionDateString = getAddDateString(newVersion.getLastRevision());
		if (StringUtils.isNotBlank(lastRevisionDateString)) {
			auditEntries.add("Electric revision performed on " + lastRevisionDateString);
		}

		return auditEntries;
	}

	public static List<String> getCalibrationAuditEntries(ApplianceCalibration previousVersion, ApplianceCalibration newVersion) {
		List<String> auditEntries = new ArrayList<>();

		addAuditEntryIfChanged(previousVersion.getInterval().toString(), newVersion.getInterval().toString(), "Calibration interval", auditEntries);

		String lastRevisionDateString = getAddDateString(newVersion.getLastCalibration());
		if (StringUtils.isNotBlank(lastRevisionDateString)) {
			auditEntries.add("Calibration performed on " + lastRevisionDateString);
		}

		return auditEntries;
	}

	private static String getAddDateString(LocalDate date) {
		if (date != null) {
			return date.format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
		} else {
			return "";
		}
	}

	private static String getAddDateString(OffsetDateTime dateTime) {
		if (dateTime != null) {
			return dateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
		} else {
			return "";
		}
	}

	private static void addAuditEntryIfChanged(String prop1, String prop2, String propertyText, List<String> auditEntries) {
		if (!StringUtils.equals(prop1, prop2)) {
			auditEntries.add(getLogItemDescription(propertyText, prop2));
		}
	}

	private static String getLogItemDescription(String propertyText, String comment) {
		if (StringUtils.isNotBlank(comment)) {
			return propertyText + " changed to '" + comment + "'";
		} else {
			return propertyText + " removed";
		}
	}
}
