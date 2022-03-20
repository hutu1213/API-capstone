package project.apicapstone.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.apicapstone.dto.account.CreateAccountDto;
import project.apicapstone.dto.account.AddRoleDto;
import project.apicapstone.dto.account.UpdateAccountDto;
import project.apicapstone.entity.Account;

import java.time.LocalDate;
import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account createAccount(CreateAccountDto dto);

    Account addRole(AddRoleDto dto);

    boolean isExistedUsername(String s);

    boolean isExisted(String id);

    Page<Account> findAllAccount(Pageable pageable);

    Object pagingFormat(Page<Account> accountPage);

    List<Account> getByRoleId(String roleId);

    List<Account> existByEmployeeId(String s);

    Account findByUsername(String username);

    boolean checkIfValidOldPassword(Account account, String oldPassword);

    void changePassword(Account account, String newPassword);

    boolean existsAccountByUsername(String s);

    List<Account> getAccountsByRoleName(String role1, String role2);

    Account getById(String accountId);


    Account getByEmployeeId(String id);

    void update(UpdateAccountDto dto);
}
