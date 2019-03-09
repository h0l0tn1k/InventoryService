package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.AuditLogFacade;
import cz.siemens.inventory.dao.InventoryServiceAuditLogDao;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.InventoryServiceAuditLog;
import cz.siemens.inventory.entity.LoginUserScd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditLogFacadeImpl implements AuditLogFacade {

	private InventoryServiceAuditLogDao inventoryServiceAuditLogDao;
	private LoginUserScdDao loginUserScdDao;

	@Autowired
	public AuditLogFacadeImpl(InventoryServiceAuditLogDao inventoryServiceAuditLogDao, LoginUserScdDao loginUserScdDao) {
		this.inventoryServiceAuditLogDao = inventoryServiceAuditLogDao;
		this.loginUserScdDao = loginUserScdDao;
	}

	@Override
	public InventoryServiceAuditLog createAuditLog(InventoryServiceAuditLog inventoryServiceAuditLog) {
		return inventoryServiceAuditLogDao.save(inventoryServiceAuditLog);
	}

	@Override
	public List<InventoryServiceAuditLog> createAuditLogs(List<InventoryServiceAuditLog> inventoryServiceAuditLogs) {
		return inventoryServiceAuditLogDao.saveAll(inventoryServiceAuditLogs);
	}

	@Override
	public void saveAuditLogEntries(List<String> descriptions, InventoryServiceAuditLog.Category category, DeviceInternal device) {
		List<InventoryServiceAuditLog> inventoryServiceAuditLogs = descriptions.stream().map(x -> initAuditLog(x, category, device)).collect(Collectors.toList());
		inventoryServiceAuditLogDao.saveAll(inventoryServiceAuditLogs);
	}

	private InventoryServiceAuditLog initAuditLog(String description, InventoryServiceAuditLog.Category category, DeviceInternal device) {
		InventoryServiceAuditLog inventoryServiceAuditLog = new InventoryServiceAuditLog();
		inventoryServiceAuditLog.setCategory(category);
		inventoryServiceAuditLog.setDescription(description);
		inventoryServiceAuditLog.setDevice(device);
		LoginUserScd loginUserScd = loginUserScdDao.getByEmail(getCurrentUsersEmail());
		inventoryServiceAuditLog.setEditingUser(loginUserScd);

		return inventoryServiceAuditLog;
	}

	private String getCurrentUsersEmail() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
