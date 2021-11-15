package project.apicapstone.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.role.AddRoleDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Role;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.RoleRepository;
import project.apicapstone.service.AccountService;

import java.util.List;
import java.util.Locale;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private PasswordEncoder encoder;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder encoder, EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.encoder = encoder;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(CreateAccountDto dto) {
//        Employee e = new Employee();
//        e= employeeRepository.findEmployeeByEmployeeId(dto.getEmployeeId());
        Account newAcc = new Account();
        //newAcc.setAccountId(dto.getEmployeeId());
        //newAcc.setEmployee(e);
        newAcc.setAccountId(dto.getAccountId());
        newAcc.setUsername(dto.getUsername());
        newAcc.setPassword(encoder.encode(dto.getPassword()));
        newAcc.setStatus(dto.getStatus().toUpperCase());


        return accountRepository.save(newAcc);
    }

    @Override
    public Account addRole(AddRoleDto dto) {
        Role role = roleRepository.getById(dto.getRoleId());
        Account account = accountRepository.getById(dto.getAccountId());

        account.addRole(role);

        return accountRepository.save(account);
    }

    @Override
    public boolean isExistedUsername(String s) {
        return accountRepository.countByUsername(s) >= 1;
    }

    @Override
    public boolean isExisted(String id) {
        return accountRepository.existsById(id);
    }
}
