package project.apicapstone.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import project.apicapstone.common.util.ResponseHandler;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.role.CreateRoleDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Role;
import project.apicapstone.service.EmployeeService;
import project.apicapstone.service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping()
    public Object findAllRole() {
        List<Role> roles = roleService.findAll();
        return ResponseHandler.getResponse(roles, HttpStatus.OK);
    }

    @PostMapping()
    public Object createRole(@Valid @RequestBody CreateRoleDto dto,
                             BindingResult errors) {
        if (errors.hasErrors())
            return ResponseHandler.getResponse(errors, HttpStatus.BAD_REQUEST);

        Role createRole = roleService.createRole(dto);

        return ResponseHandler.getResponse(createRole, HttpStatus.CREATED);
    }
}
