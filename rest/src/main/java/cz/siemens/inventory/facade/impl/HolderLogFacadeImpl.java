package cz.siemens.inventory.facade.impl;

import cz.siemens.inventory.dao.HolderChangelogDao;
import cz.siemens.inventory.entity.HolderChangelog;
import cz.siemens.inventory.facade.HolderLogFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolderLogFacadeImpl implements HolderLogFacade {

	private HolderChangelogDao holderChangelogDao;

	@Autowired
	public HolderLogFacadeImpl(HolderChangelogDao holderChangelogDao) {
		this.holderChangelogDao = holderChangelogDao;
	}

	@Override
	public HolderChangelog createChangeLog(HolderChangelog holderChangelog) {
		return holderChangelogDao.save(holderChangelog);
	}
}
