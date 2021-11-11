package project.apicapstone.service.impl;

import org.springframework.stereotype.Service;
import project.apicapstone.dto.role.CreateRoleDto;
import project.apicapstone.entity.Role;
import project.apicapstone.repository.RoleRepository;
import project.apicapstone.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(CreateRoleDto dto) {
        Role newRole = new Role();
        newRole.setRoleId(dto.getRoleId());
        newRole.setRoleName(dto.getRoleName());
        newRole.setRoleDescription(dto.getRoleDescription());
        return roleRepository.save(newRole);
    }

    @Override
    public boolean isExisted(String id) {
        return roleRepository.existsById(id);
    }
}
