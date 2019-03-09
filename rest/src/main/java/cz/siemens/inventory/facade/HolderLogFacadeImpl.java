package cz.siemens.inventory.facade;

import cz.siemens.inventory.api.facade.HolderLogFacade;
import cz.siemens.inventory.dao.HolderChangelogDao;
import cz.siemens.inventory.entity.HolderChangelog;
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
