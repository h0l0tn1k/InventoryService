package cz.siemens.inventory.api.facade;

import cz.siemens.inventory.entity.HolderChangelog;

public interface HolderLogFacade {

	HolderChangelog createChangeLog(HolderChangelog holderChangelog);
}
