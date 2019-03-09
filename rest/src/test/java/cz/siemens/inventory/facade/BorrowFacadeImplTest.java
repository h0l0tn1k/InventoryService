package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.BorrowFacade;
import cz.siemens.inventory.api.gen.model.BorrowReturn;
import cz.siemens.inventory.api.gen.model.Device;
import cz.siemens.inventory.api.gen.model.User;
import cz.siemens.inventory.api.mapper.DeviceStateMapper;
import cz.siemens.inventory.dao.DeviceDao;
import cz.siemens.inventory.dao.HolderChangelogDao;
import cz.siemens.inventory.dao.LoginUserScdDao;
import cz.siemens.inventory.entity.DeviceInternal;
import cz.siemens.inventory.entity.HolderChangelog;
import cz.siemens.inventory.entity.LoginUserScd;
import cz.siemens.inventory.exception.ConflictException;
import cz.siemens.inventory.exception.NotFoundException;
import cz.siemens.inventory.mapper.DeviceStateMapperImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

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

	@Test
	public void borrowReturnDevice_withAssignedOwner_borrowedToNewHolder() throws Exception {
		String expectedComment = "Borrowed by me";
		DeviceInternal deviceInternal = new DeviceInternal();
		deviceInternal.setOwner(new LoginUserScd());

		LoginUserScd newHolder = new LoginUserScd();
		newHolder.setId(3L);
		newHolder.setFirstName("Tester");
		newHolder.setLastName("New Holder");

		doReturn(Optional.of(deviceInternal)).when(deviceDao).findById(anyLong());
		doReturn(Optional.of(newHolder)).when(loginUserScdDao).findById(3L);

		ArgumentCaptor<HolderChangelog> changelogArgumentCaptor = ArgumentCaptor.forClass(HolderChangelog.class);

		cut.borrowReturnDevice(
				new BorrowReturn()
						.device(new Device().id(3L))
						.newHolder(new User().id(3L))
						.comment(expectedComment)
		);

		verify(holderChangelogDao).save(changelogArgumentCaptor.capture());

		HolderChangelog actualChangeLog = changelogArgumentCaptor.getValue();
		assertThat(actualChangeLog.getComment()).isEqualTo(expectedComment);
		assertThat(actualChangeLog.getDevice()).isEqualTo(deviceInternal);
		assertThat(actualChangeLog.getNewHolder()).isEqualTo(newHolder);
		assertThat(actualChangeLog.getOldHolder()).isEqualTo(new LoginUserScd());
	}

	@Test
	public void borrowReturnDevice_withAssignedOwnerAndHolder_returnDevice() throws Exception {
		String expectedComment = "Returned to owner";
		Long expectedDeviceId = 3L;
		LoginUserScd owner = new LoginUserScd();
		owner.setId(44L);
		owner.setFirstName("Tester");
		owner.setLastName("Owner");
		DeviceInternal deviceInternal = new DeviceInternal();
		deviceInternal.setId(expectedDeviceId);
		deviceInternal.setOwner(owner);
		deviceInternal.setHolder(new LoginUserScd());

		doReturn(Optional.of(deviceInternal)).when(deviceDao).findById(anyLong());

		ArgumentCaptor<HolderChangelog> changelogArgumentCaptor = ArgumentCaptor.forClass(HolderChangelog.class);
		ArgumentCaptor<LoginUserScd> userScdArgumentCaptor = ArgumentCaptor.forClass(LoginUserScd.class);
		ArgumentCaptor<Long> deviceIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

		cut.borrowReturnDevice(
				new BorrowReturn()
						.device(new Device().id(expectedDeviceId))
						.comment(expectedComment)
		);

		verify(holderChangelogDao).save(changelogArgumentCaptor.capture());

		HolderChangelog actualChangeLog = changelogArgumentCaptor.getValue();
		assertThat(actualChangeLog.getComment()).isEqualTo(expectedComment);
		assertThat(actualChangeLog.getDevice()).isEqualTo(deviceInternal);
		assertThat(actualChangeLog.getNewHolder()).isEqualTo(owner);
		assertThat(actualChangeLog.getOldHolder()).isEqualTo(new LoginUserScd());

		verify(deviceDao).updateHolder(userScdArgumentCaptor.capture(), deviceIdArgumentCaptor.capture());
		assertThat(userScdArgumentCaptor.getValue()).isEqualTo(null);
		assertThat(deviceIdArgumentCaptor.getValue()).isEqualTo(expectedDeviceId);

	}

	@Test
	public void borrowReturnDevice_withAssignedOwnerAndHolderBorrowingByOtherUser_borrowsDevice() throws Exception {
		String expectedComment = "I need it now";
		Long expectedDeviceId = 3L;

		LoginUserScd owner = new LoginUserScd();
		owner.setId(3L);
		owner.setFirstName("Tester");
		owner.setLastName("Owner");

		LoginUserScd holder = new LoginUserScd();
		holder.setId(2L);
		holder.setFirstName("Tester");
		holder.setLastName("Current Holder");

		LoginUserScd newHolder = new LoginUserScd();
		newHolder.setId(6L);
		newHolder.setFirstName("Tester");
		newHolder.setLastName("New Holder");

		DeviceInternal deviceInternal = new DeviceInternal();
		deviceInternal.setId(expectedDeviceId);
		deviceInternal.setOwner(owner);
		deviceInternal.setHolder(holder);

		doReturn(Optional.of(deviceInternal)).when(deviceDao).findById(anyLong());
		doReturn(Optional.of(newHolder)).when(loginUserScdDao).findById(newHolder.getId());

		ArgumentCaptor<HolderChangelog> changelogArgumentCaptor = ArgumentCaptor.forClass(HolderChangelog.class);
		ArgumentCaptor<LoginUserScd> userScdArgumentCaptor = ArgumentCaptor.forClass(LoginUserScd.class);
		ArgumentCaptor<Long> deviceIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

		cut.borrowReturnDevice(
				new BorrowReturn()
						.device(new Device().id(expectedDeviceId))
						.newHolder(new User().id(newHolder.getId()))
						.comment(expectedComment)
		);

		verify(holderChangelogDao).save(changelogArgumentCaptor.capture());

		HolderChangelog actualChangeLog = changelogArgumentCaptor.getValue();
		assertThat(actualChangeLog.getComment()).isEqualTo(expectedComment);
		assertThat(actualChangeLog.getDevice()).isEqualTo(deviceInternal);
		assertThat(actualChangeLog.getNewHolder()).isEqualTo(newHolder);
		assertThat(actualChangeLog.getOldHolder()).isEqualTo(holder);

		verify(deviceDao).updateHolder(userScdArgumentCaptor.capture(), deviceIdArgumentCaptor.capture());
		assertThat(userScdArgumentCaptor.getValue()).isEqualTo(newHolder);
		assertThat(deviceIdArgumentCaptor.getValue()).isEqualTo(expectedDeviceId);

	}

	@Test
	public void borrowReturnDevice_withAssignedOwnerAndHolderBorrowingByOwner_returnsDevice() throws Exception {
		String expectedComment = "Returning to Owner";
		Long expectedDeviceId = 3L;

		LoginUserScd owner = new LoginUserScd();
		owner.setId(3L);
		owner.setFirstName("Tester");
		owner.setLastName("Owner");

		LoginUserScd holder = new LoginUserScd();
		holder.setId(2L);
		holder.setFirstName("Tester");
		holder.setLastName("Current Holder");

		DeviceInternal deviceInternal = new DeviceInternal();
		deviceInternal.setId(expectedDeviceId);
		deviceInternal.setOwner(owner);
		deviceInternal.setHolder(holder);

		doReturn(Optional.of(deviceInternal)).when(deviceDao).findById(anyLong());
		doReturn(Optional.of(owner)).when(loginUserScdDao).findById(owner.getId());

		ArgumentCaptor<HolderChangelog> changelogArgumentCaptor = ArgumentCaptor.forClass(HolderChangelog.class);
		ArgumentCaptor<LoginUserScd> userScdArgumentCaptor = ArgumentCaptor.forClass(LoginUserScd.class);
		ArgumentCaptor<Long> deviceIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

		cut.borrowReturnDevice(
				new BorrowReturn()
						.device(new Device().id(expectedDeviceId))
						.newHolder(new User().id(owner.getId()))
						.comment(expectedComment)
		);

		verify(holderChangelogDao).save(changelogArgumentCaptor.capture());

		HolderChangelog actualChangeLog = changelogArgumentCaptor.getValue();
		assertThat(actualChangeLog.getComment()).isEqualTo(expectedComment);
		assertThat(actualChangeLog.getDevice()).isEqualTo(deviceInternal);
		assertThat(actualChangeLog.getNewHolder()).isEqualTo(owner);
		assertThat(actualChangeLog.getOldHolder()).isEqualTo(holder);

		verify(deviceDao).updateHolder(userScdArgumentCaptor.capture(), deviceIdArgumentCaptor.capture());
		assertThat(userScdArgumentCaptor.getValue()).isEqualTo(null);
		assertThat(deviceIdArgumentCaptor.getValue()).isEqualTo(expectedDeviceId);

	}
}