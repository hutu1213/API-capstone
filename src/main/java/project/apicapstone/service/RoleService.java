package project.apicapstone.service;

import org.springframework.stereotype.Service;
import project.apicapstone.dto.role.CreateRoleDto;
import project.apicapstone.entity.Role;

import java.util.List;

@Service
public interface RoleService {
    List<Role> findAll();

    Role createRole(CreateRoleDto dto);

    boolean isExisted(String id);


}
