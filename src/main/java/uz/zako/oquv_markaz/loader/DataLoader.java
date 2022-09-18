package uz.zako.oquv_markaz.loader;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.zako.oquv_markaz.entity.Admin;
import uz.zako.oquv_markaz.entity.Role;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.repository.AdminRepository;
import uz.zako.oquv_markaz.repository.RoleRepository;
import uz.zako.oquv_markaz.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)--hammasini private qilib oladi qoymasak ham
public class DataLoader implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AdminRepository adminRepository;


    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    @Override
    public void run(String... args) throws Exception {

        try {
            if (init.equalsIgnoreCase("create")) {
                Role roleUser = new Role();
                roleUser.setName("ROLE_USER");
                roleUser.setId(1L);
                Role roleAdmin = new Role(2L, "ROLE_ADMIN");
                List<Role> roleList = new ArrayList<>(Arrays.asList(roleUser, roleAdmin));
                roleRepository.saveAll(roleList);
                Admin admin = new Admin();
                admin.setAdmin(true);
                admin.setFullName("admin");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setSocial("t.me/test");
                admin.setRoles(roleList);
                adminRepository.save(admin);
                System.out.println("Admin saqlandi");

            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

    }
}
