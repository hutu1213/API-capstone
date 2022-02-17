package project.apicapstone.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Role;
import project.apicapstone.entity.Task;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    int countByRoleName(String roleName);

    @Query("SELECT r FROM Role r WHERE r.roleName LIKE %?1% OR r.roleId LIKE %?1%")
    List<Role> findRolesByNameOrId(String paramSearch);

    @Transactional(readOnly = true)
    @Query("SELECT r FROM Role r")
    Page<Role> findAllRole(Pageable pageable);

    @Query("SELECT r FROM Role r join r.accounts a WHERE a.username LIKE ?1 ")
    List<Role> findByUsername(String username);
}
