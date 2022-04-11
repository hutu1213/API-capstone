package project.apicapstone.common.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import project.apicapstone.entity.Role;
import project.apicapstone.repository.RoleRepository;


@Component
public class ApplicationConfig implements ApplicationListener<ApplicationReadyEvent> {
    private final RoleRepository roleRepository;

    public ApplicationConfig(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        init();
    }

    private void init() {
        if (roleRepository.findAll().isEmpty()) {
            Role role = new Role();
            role.setRoleId("1");
            role.setRoleName("ROLE_TRUONGPHONG");
            role.setRoleDescription(null);
            roleRepository.save(role);
        }

    }
}
