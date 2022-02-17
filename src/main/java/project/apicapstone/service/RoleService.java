package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.role.CreateRoleDto;
import project.apicapstone.dto.role.UpdateRoleDto;
import project.apicapstone.entity.Role;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAll();

    Role createRole(CreateRoleDto dto);

    boolean isExisted(String id);


    boolean isExistedRoleName(String s);

    Role findRoleById(String id);

    List<Role> findRoleByNameOrId(String paramSearch);

    void deleteById(String id);

    void updateRole(UpdateRoleDto dto, String roleId);

    Page<Role> findAllRole(Pageable pageable);

    Object pagingFormat(Page<Role> rolePage);
}
