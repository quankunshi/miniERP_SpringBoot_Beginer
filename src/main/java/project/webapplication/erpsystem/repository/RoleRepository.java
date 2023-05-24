package project.webapplication.erpsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.webapplication.erpsystem.models.Admin.Role;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
