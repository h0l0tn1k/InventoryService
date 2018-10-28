package cz.siemens.inventory.facade;

import cz.siemens.inventory.entity.HolderChangelog;

public interface HolderLogFacade {

	HolderChangelog createChangeLog(HolderChangelog holderChangelog);
}
