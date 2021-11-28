package project.apicapstone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.apicapstone.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    int countByRoleName(String roleName);
}
