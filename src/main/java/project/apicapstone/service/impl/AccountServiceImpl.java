package project.apicapstone.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Employee;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private PasswordEncoder encoder;
    private EmployeeRepository employeeRepository;

    public AccountServiceImpl(AccountRepository accountRepository,PasswordEncoder encoder,EmployeeRepository employeeRepository) {
        this.accountRepository=accountRepository;
        this.encoder=encoder;
        this.employeeRepository=employeeRepository;
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    @Override
    public Account createAccount(CreateAccountDto dto) {
        Employee e = new Employee();
        e= employeeRepository.findEmployeeByEmployeeId(dto.getEmployeeId());
        Account newAcc = new Account();
        //newAcc.setAccountId(dto.getEmployeeId());
        //newAcc.setEmployee(e);
        newAcc.setUsername(dto.getUsername());
        newAcc.setPassword(encoder.encode(dto.getPassword()));
        newAcc.setStatus(dto.getStatus());


        return accountRepository.save(newAcc);
    }
}
