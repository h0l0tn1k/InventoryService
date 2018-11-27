package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.dao.HolderChangelogDao;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.HolderChangelog;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.exception.ConflictException;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.facade.BorrowFacade;
import cz.siemens.inventory.gen.model.BorrowReturn;
import cz.siemens.inventory.mapper.DeviceStateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Optional;

@Service
@Transactional
public class BorrowFacadeImpl implements BorrowFacade {

	private DeviceDao deviceDao;
	private LoginUserScdDao loginUserScdDao;
	private HolderChangelogDao holderChangelogDao;
	private DeviceStateMapper deviceStateMapper;

	@Autowired
	public BorrowFacadeImpl(DeviceDao deviceDao, LoginUserScdDao loginUserScdDao, HolderChangelogDao holderChangelogDao,
							DeviceStateMapper deviceStateMapper) {
		this.deviceDao = deviceDao;
		this.loginUserScdDao = loginUserScdDao;
		this.holderChangelogDao = holderChangelogDao;
		this.deviceStateMapper = deviceStateMapper;
	}

	@Override
	public void borrowReturnDevice(BorrowReturn borrowReturn) {
		Optional<DeviceInternal> deviceFromDbOptional = deviceDao.findById(borrowReturn.getDevice().getId());
		throwExceptionIfNotPresent(deviceFromDbOptional, "Device with id=" + borrowReturn.getDevice().getId() + " not found.");

		boolean isNewHolderSpecified = borrowReturn.getNewHolder() != null;
		Optional<LoginUserScd> newHolderFromDbOptional = Optional.empty();
		if (isNewHolderSpecified) {
			newHolderFromDbOptional = loginUserScdDao.findById(borrowReturn.getNewHolder().getId());
			throwExceptionIfNotPresent(newHolderFromDbOptional, "User with id=" + borrowReturn.getNewHolder().getId() + " not found.");
		}

		DeviceInternal deviceFromDb = deviceFromDbOptional.get();
		if (deviceFromDb.getOwner() == null) {
			throw new ConflictException("Not possible to set holder until owner of this device is set.");
		}

		HolderChangelog holderChangelog = new HolderChangelog();
		holderChangelog.setChangeDate(new Timestamp(System.currentTimeMillis()));
		holderChangelog.setDevice(deviceFromDb);
		holderChangelog.setComment(borrowReturn.getComment());

		//if holder == null => owner is holder (for audit log)
		LoginUserScd realOldHolder = (deviceFromDb.getHolder() == null) ? deviceFromDb.getOwner() : deviceFromDb.getHolder();
		holderChangelog.setOldHolder(realOldHolder);
		deviceFromDb.setDeviceState(deviceStateMapper.mapToInternal(borrowReturn.getDevice().getDeviceState()));

		//Business Logic is
		//Device has Owner and Holder
		//if holder is not specified == null => Owner is current holder but DB table for holder stays null
		//if holder is set to same as owner => set holder = null
		//so if holder is null, in audit log we should display owner as previous holder

		//device exists, new holder exists or is null
		if (isNewHolderSpecified) {
			LoginUserScd newHolderFromDb = newHolderFromDbOptional.get();
			if (!newHolderFromDb.equals(deviceFromDb.getHolder())) {
				if(deviceFromDb.getHolder() == null) {
					if (!newHolderFromDb.equals(deviceFromDb.getOwner())) {
						//new holder is specified, old holder is null == owner is holder and new holder is not owner
						//device table set holder new holder, new holder in audit is new holder
						deviceFromDb.setHolder(newHolderFromDb);
						holderChangelog.setNewHolder(newHolderFromDb);
						saveDeviceAndAuditLog(deviceFromDb, holderChangelog);
					}
				} else {
					if (newHolderFromDb.equals(deviceFromDb.getOwner())) {
						//new holder is specified, old holder is specified, new holder equals owner
						//device table set holder null, new holder in audit is owner
						deviceFromDb.setHolder(null);
						holderChangelog.setNewHolder(deviceFromDb.getOwner());
						saveDeviceAndAuditLog(deviceFromDb, holderChangelog);
					} else {
						//new holder is specified, old holder is specified, new holder not equals owner
						//device table set holder null, new holder in audit is owner
						deviceFromDb.setHolder(newHolderFromDb);
						holderChangelog.setNewHolder(deviceFromDb.getHolder());
						saveDeviceAndAuditLog(deviceFromDb, holderChangelog);
					}
				}
			}
		} else {
			if (deviceFromDb.getHolder() != null) {
				//new holder is null => set null holder for device table but set owner as holder in audit log
				deviceFromDb.setHolder(null);
				holderChangelog.setNewHolder(deviceFromDb.getOwner());
				saveDeviceAndAuditLog(deviceFromDb, holderChangelog);
			}
		}
	}

	private void saveDeviceAndAuditLog(DeviceInternal deviceFromDb, HolderChangelog holderChangelog) {
		deviceDao.updateHolder(deviceFromDb.getHolder(), deviceFromDb.getId());
		holderChangelogDao.save(holderChangelog);
	}

	private <T> void throwExceptionIfNotPresent(Optional<T> entity, String message) {
		if (!entity.isPresent()) {
			throw new NotFoundException(message);
		}
	}
}
