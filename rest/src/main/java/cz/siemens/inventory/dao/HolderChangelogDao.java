package cz.siemens.inventory.dao;

import java.util.List;

import cz.siemens.inventory.entity.HolderChangelog;

public class HolderChangelogDao extends GenericDao<HolderChangelog>
{
	public HolderChangelogDao()
	{
		super(HolderChangelog.class);
	}

	public List<HolderChangelog> getDeviceChanges(long deviceId)
	{
		return em.createQuery("FROM HolderChangelog WHERE object_id = " + deviceId, HolderChangelog.class).getResultList();
	}
	
	public List<HolderChangelog> getUsersLentDevices(long newHolderId)
	{
		return em.createQuery("FROM HolderChangelog WHERE new_holder_id = " + newHolderId, HolderChangelog.class).getResultList();
	}
	
	public List<HolderChangelog> getUsersReturnedDevices(long oldHolderId)
	{
		return em.createQuery("FROM HolderChangelog WHERE old_holder_id = " + oldHolderId, HolderChangelog.class).getResultList();
	}
}
