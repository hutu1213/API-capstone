package project.apicapstone.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.account.PagingFormatAccountDto;
import project.apicapstone.dto.account.AddRoleDto;
import project.apicapstone.dto.account.UpdateAccountDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Notification;
import project.apicapstone.entity.Role;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.RoleRepository;
import project.apicapstone.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private PasswordEncoder encoder;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private static final String STATUS = "ACTIVE";

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
        Account newAcc = new Account();
        newAcc.setUsername(dto.getUsername());
        newAcc.setPassword(encoder.encode(dto.getPassword()));
        newAcc.setStatus(STATUS);
        Employee employee = employeeRepository.getById(dto.getEmployeeId());
        newAcc.setEmployee(employee);
        newAcc.setRole(roleRepository.getById(dto.getRoleId()));
        return accountRepository.save(newAcc);
    }

    @Override
    public Account addRole(AddRoleDto dto) {
//        Role role = roleRepository.getById(dto.getRoleId());
//        Account account = accountRepository.getById(dto.getAccountId());
//
//        account.addRole(role);
//
//        return accountRepository.save(account);
        return null;
    }

    @Override
    public boolean isExistedUsername(String s) {
        return accountRepository.countByUsername(s) >= 1;
    }

    @Override
    public boolean isExisted(String id) {
        return accountRepository.existsById(id);
    }

    @Override
    public Page<Account> findAllAccount(Pageable pageable) {
        return accountRepository.findAllAccount(pageable);
    }

    @Override
    public Object pagingFormat(Page<Account> accountPage) {
        PagingFormatAccountDto dto = new PagingFormatAccountDto();
        dto.setPageSize(accountPage.getSize());
        dto.setTotalRecordCount(accountPage.getTotalElements());
        dto.setPageNumber(accountPage.getNumber());
        dto.setRecords(accountPage.toList());
        return dto;
    }

    @Override
    public List<Account> getByRoleId(String roleId) {
        return accountRepository.findAccountsByRoleId(roleId);
    }

    @Override
    public List<Account> existByEmployeeId(String s) {
        return accountRepository.findEmployeeIdInAccount(s);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public boolean checkIfValidOldPassword(Account account, String oldPassword) {
        return encoder.matches(oldPassword, account.getPassword());
    }

    @Override
    public void changePassword(Account account, String newPassword) {
        account.setPassword(encoder.encode(newPassword));
        accountRepository.save(account);
    }

    @Override
    public boolean existsAccountByUsername(String s) {
        return accountRepository.existsAccountByUsername(s);
    }

    @Override
    public List<Account> getAccountsByRoleName(String role1, String role2) {
        return accountRepository.getAccountsByRoleName(role1, role2);
    }

    @Override
    public Account getById(String accountId) {
        return accountRepository.getById(accountId);
    }

    @Override
    public Account getByEmployeeId(String id) {
        return accountRepository.getAccountByEmployee_EmployeeId(id);
    }

    @Override
    public void update(UpdateAccountDto dto) {
        Account account = accountRepository.getById(dto.getAccountId());
        account.setStatus(dto.getStatus());
        account.setRole(roleRepository.getById(dto.getRoleId()));
        accountRepository.save(account);
    }

    @Override
    public List<Notification> getNotificationById(String id) {
        return null;
    }
}
