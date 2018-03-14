package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Device;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class DeviceDaoImpl extends GenericDao<Device> {

    public DeviceDaoImpl() {
        super(Device.class);
    }

    public Device findDeviceByBarcodeId(String barcodeId) {
        return em.createQuery("SELECT d FROM Device d WHERE d.barcodeNumber LIKE :barcode", Device.class)
                .setParameter("barcode", barcodeId).getSingleResult();
    }

    public Device findDeviceBySerialNo(String serialNo) {
        return em.createQuery("SELECT d FROM Device d WHERE d.serialNumber LIKE :serialNo", Device.class)
                .setParameter("serialNo", serialNo).getSingleResult();
    }
}
