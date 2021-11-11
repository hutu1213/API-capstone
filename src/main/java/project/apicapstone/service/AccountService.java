package project.apicapstone.service;

import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.role.AddRoleDto;
import project.apicapstone.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account createAccount(CreateAccountDto dto);

    Account addRole(AddRoleDto dto);
}
