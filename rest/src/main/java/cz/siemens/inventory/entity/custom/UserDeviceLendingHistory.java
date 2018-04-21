package cz.siemens.inventory.entity.custom;

import cz.siemens.inventory.entity.Device;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class UserDeviceLendingHistory
{
	Device device;
	Timestamp dateFrom;
	Timestamp dateTo;
	SimpleDateFormat dateFormat;
	
	public UserDeviceLendingHistory(Device device, Timestamp dateFrom, Timestamp dateTo)
	{
		this.device = device;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		//TODO: Vytahnout do konfigurace
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public String getDeviceBarcode()
	{
		return device.getBarcodeNumber();
	}
	
	public String getDeviceCompleteName()
	{
		String typeAndVersion = device.getTypeAndVersionName();
		if(typeAndVersion != null && typeAndVersion.length() > 0)
		{
			String serial = device.getSerialNumber();
			if(serial != null && serial.length() > 0)
			{
				typeAndVersion += ", SN: " + serial; 
			}
		}
		return typeAndVersion;
	}
	
	public String getStartingDateString()
	{
		String dateStr = dateFormat.format(dateFrom);
		return dateStr;
	}
	
	public String getEndingDateString()
	{
		if(dateTo != null)
		{
			String dateStr = dateFormat.format(dateTo);
			return dateStr;
		}
		return "";
	}
}
