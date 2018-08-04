package cz.siemens.inventory.dao;

import cz.siemens.inventory.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsDao extends JpaRepository<Project, Long> {
}
