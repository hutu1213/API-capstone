package project.apicapstone.common.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.apicapstone.entity.*;
import project.apicapstone.repository.*;


@Component
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final TitleRepository titleRepository;
    private final AreaRepository areaRepository;

    public ApplicationConfig(RoleRepository roleRepository, AccountRepository accountRepository, PasswordEncoder passwordEncoder, EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, PositionRepository positionRepository, TitleRepository titleRepository, AreaRepository areaRepository) {
        this.roleRepository = roleRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.titleRepository = titleRepository;
        this.areaRepository = areaRepository;
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
            addEmployee.setEmail(null);
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
            Department department1 = new Department();
            department1.setDepartmentId("PNS");
            department1.setDepartmentName("Phòng Nhân Sự");
            departmentRepository.save(department1);
            Department department2 = new Department();
            department2.setDepartmentId("KETOAN");
            department2.setDepartmentName("Phòng Kế Toán");
            departmentRepository.save(department2);
            Department department3 = new Department();
            department3.setDepartmentId("KIEMTOAN");
            department3.setDepartmentName("Phòng Kiểm Toán");
            departmentRepository.save(department3);
            Department department4 = new Department();
            department4.setDepartmentId("PKD");
            department4.setDepartmentName("Phòng Kinh Doanh");
            departmentRepository.save(department4);
            Position position1 = new Position();
            position1.setPositionId("NV");
            position1.setPositionName("Nhân Viên");
            positionRepository.save(position1);
            Position position2 = new Position();
            position2.setPositionId("TP");
            position2.setPositionName("Trưởng Phòng");
            positionRepository.save(position2);
            Position position3 = new Position();
            position3.setPositionId("TTS");
            position3.setPositionName("Thực Tập Sinh");
            positionRepository.save(position3);
            Title title1 = new Title();
            title1.setTitleId("TPNS");
            title1.setJobTitle("Trưởng Phòng Nhân Sự");
            titleRepository.save(title1);
            Title title2 = new Title();
            title2.setTitleId("NVNS");
            title2.setJobTitle("Nhân Viên Nhân Sự");
            titleRepository.save(title2);
            Title title3 = new Title();
            title3.setTitleId("TPKT");
            title3.setJobTitle("Trưởng Phòng Kế Toán");
            titleRepository.save(title3);

            Area area = new Area();
            area.setAreaId("KV1");
            area.setName("Đông Trung Tây");
            areaRepository.save(area);

            Area area2 = new Area();
            area2.setAreaId("KV2");
            area2.setName("Miển Bắc");
            areaRepository.save(area2);

            Area area3 = new Area();
            area3.setAreaId("KV3");
            area3.setName("Miển Nam");
            areaRepository.save(area3);
        }

    }
}
