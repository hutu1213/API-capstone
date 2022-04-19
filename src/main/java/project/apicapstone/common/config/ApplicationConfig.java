package project.apicapstone.common.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.apicapstone.entity.Account;
import project.apicapstone.entity.Employee;
import project.apicapstone.entity.Role;
import project.apicapstone.repository.AccountRepository;
import project.apicapstone.repository.EmployeeRepository;
import project.apicapstone.repository.RoleRepository;


@Component
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;

    public ApplicationConfig(RoleRepository roleRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository) {
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        init();
    }

    private void init() {
        if (roleRepository.findAll().isEmpty()) {
            Employee addEmployee = new Employee();
            addEmployee.setEmployeeId("admin");
            addEmployee.setEmployeeName("admin");
            addEmployee.setDateBirth(null);
            addEmployee.setPlaceBirth(null);
            addEmployee.setPhone(null);
            addEmployee.setGender(null);
            addEmployee.setAddress(null);
            addEmployee.setEmail("admin@gmail.com");
            addEmployee.setNationality(null);
            addEmployee.setReligion(null);
            addEmployee.setEthnic(null);
            addEmployee.setAcademicLevel(null);
            addEmployee.setBank(null);
            addEmployee.setBankAccountNo(null);
            addEmployee.setTaxIdentificationNo(null);
            addEmployee.setMaritalStatus(null);
            addEmployee.setWorkingStatus(null);
            addEmployee.setAvatar(null);
            addEmployee.setCreateDate(null);
            addEmployee.setUpdateDate(null);
            addEmployee.setDayOfBirth(0);
            addEmployee.setMonthOfBirth(0);
            addEmployee.setBackIdentityCard(null);
            addEmployee.setFrontIdentityCard(null);
            addEmployee.setPlaceIssue(null);
            addEmployee.setDateIssue(null);
            addEmployee.setWorkplace(null);
            addEmployee.setTitle(null);
            // account
            Account account = new Account();
            account.setEmployee(addEmployee);
            account.setPassword(passwordEncoder.encode("admin123456"));
            account.setStatus("ACTIVE");
            account.setUsername("admin");
            // role
            Role role1 = new Role();
            role1.setRoleId("1");
            role1.setRoleName("ROLE_TRUONGPHONG");
            roleRepository.save(role1);
            account.setRole(role1);
            accountRepository.save(account);
            addEmployee.setAccount(account);
            employeeRepository.save(addEmployee);
            Role role2 = new Role();
            role2.setRoleId("2");
            role2.setRoleName("ROLE_QL_NHANVIEN");
            roleRepository.save(role2);
            Role role3 = new Role();
            role3.setRoleId("3");
            role3.setRoleName("ROLE_QL_HOPDONG");
            roleRepository.save(role3);
            Role role4 = new Role();
            role4.setRoleId("4");
            role4.setRoleName("ROLE_QL_TUYENDUNG");
            roleRepository.save(role4);
            Role role5 = new Role();
            role5.setRoleId("5");
            role5.setRoleName("ROLE_TRUONGPHONGBAN_KHAC");
            roleRepository.save(role5);
        }

    }
}
