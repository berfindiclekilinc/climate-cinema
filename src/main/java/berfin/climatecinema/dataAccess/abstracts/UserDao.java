package berfin.climatecinema.dataAccess.abstracts;

import berfin.climatecinema.entities.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
}
