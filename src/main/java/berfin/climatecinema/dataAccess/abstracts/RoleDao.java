package berfin.climatecinema.dataAccess.abstracts;

import berfin.climatecinema.entities.concretes.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
