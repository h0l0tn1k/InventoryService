package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.HolderChangelog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolderChangelogDao extends JpaRepository<HolderChangelog, Long> {
}
