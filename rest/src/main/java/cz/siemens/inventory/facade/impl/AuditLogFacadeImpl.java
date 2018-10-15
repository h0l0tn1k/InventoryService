package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.AuditLogDao;
import cz.siemens.inventory.entity.AuditLog;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.facade.AuditLogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditLogFacadeImpl implements AuditLogFacade {

    private AuditLogDao auditLogDao;

    @Autowired
    public AuditLogFacadeImpl(AuditLogDao auditLogDao) {
        this.auditLogDao = auditLogDao;
    }

    @Override
    public AuditLog createAuditLog(AuditLog auditLog) {
        return auditLogDao.save(auditLog);
    }

    @Override
    public List<AuditLog> createAuditLogs(List<AuditLog> auditLogs) {
        return auditLogDao.saveAll(auditLogs);
    }

    @Override
    public void saveAuditLogEntries(List<String> descriptions, AuditLog.Category category, DeviceInternal device) {
        List<AuditLog> auditLogs = descriptions.stream().map(x -> initAuditLog(x, category, device)).collect(Collectors.toList());
        auditLogDao.saveAll(auditLogs);
    }

    private AuditLog initAuditLog(String description, AuditLog.Category category, DeviceInternal device) {
        AuditLog auditLog = new AuditLog();
        auditLog.setCategory(category);
        auditLog.setDescription(description);
        auditLog.setDevice(device);
        //todo add auditLog.setEditingUser();
        LoginUserScd loginUserScd = new LoginUserScd();
        loginUserScd.setId(447L);
        auditLog.setEditingUser(loginUserScd);

        return auditLog;
    }
}
