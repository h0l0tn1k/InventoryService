package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.dao.HolderChangelogDao;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.exception.ConflictException;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.facade.BorrowFacade;
import cz.siemens.inventory.gen.model.BorrowReturn;
import cz.siemens.inventory.gen.model.Device;
import cz.siemens.inventory.gen.model.User;
import cz.siemens.inventory.mapper.DeviceStateMapper;
import cz.siemens.inventory.mapper.impl.DeviceStateMapperImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;

public class BorrowFacadeImplTest {

	private DeviceDao deviceDao;
	private LoginUserScdDao loginUserScdDao;
	private HolderChangelogDao holderChangelogDao;
	private DeviceStateMapper stateMapper = new DeviceStateMapperImpl();

	private BorrowFacade cut;

	@Before
	public void setUp() throws Exception {
		deviceDao = Mockito.mock(DeviceDao.class);
		loginUserScdDao = Mockito.mock(LoginUserScdDao.class);
		holderChangelogDao = Mockito.mock(HolderChangelogDao.class);
		cut = new BorrowFacadeImpl(deviceDao, loginUserScdDao, holderChangelogDao, stateMapper);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = NotFoundException.class)
	public void borrowReturnDevice_nonExistingDevice_throwsNotFoundException() throws Exception {

		doReturn(Optional.empty()).when(deviceDao).findById(anyLong());

		cut.borrowReturnDevice(new BorrowReturn().device(new Device().id(1L)));
	}

	@Test(expected = NotFoundException.class)
	public void borrowReturnDevice_nonExistingNewHolder_throwsNotFoundException() throws Exception {

		doReturn(Optional.of(new DeviceInternal())).when(deviceDao).findById(anyLong());
		doReturn(Optional.empty()).when(loginUserScdDao).findById(anyLong());

		cut.borrowReturnDevice(new BorrowReturn().device(new Device().id(1L)).newHolder(new User()));
	}

	@Test(expected = ConflictException.class)
	public void borrowReturnDevice_nonAssignedOwner_throwsConflictException() throws Exception {

		doReturn(Optional.of(new DeviceInternal())).when(deviceDao).findById(anyLong());
		doReturn(Optional.of(new LoginUserScd())).when(loginUserScdDao).findById(anyLong());

		cut.borrowReturnDevice(new BorrowReturn().device(new Device().id(1L)).newHolder(new User().id(3L)));
	}

}