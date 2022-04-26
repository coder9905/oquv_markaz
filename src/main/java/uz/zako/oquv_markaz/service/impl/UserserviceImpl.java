package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Admin;
import uz.zako.oquv_markaz.entity.Groups;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.GroupPayload;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.repository.AdminRepository;
import uz.zako.oquv_markaz.repository.GroupsRepository;
import uz.zako.oquv_markaz.repository.RoleRepository;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.service.UserService;

import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserserviceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private  final GroupsRepository groupsRepository;
    private final AdminRepository adminRepository;
    @Override
    public boolean save(UserPayload payload) {
        try {
            User user = new User();
            user.setFullName(payload.getFullName());
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user.setUsername(payload.getUsername());
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            System.out.println(user.toString() + "=keldi");
            User user1 = userRepository.save(user);
            if (user1 != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("save User error - {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean saveUserIdGroup(GroupPayload payload, Long userId) {
        try {
            User user=new User();
            user.setGroup(Arrays.asList(groupsRepository.findByName(payload.getName())));
            return true;
        } catch (Exception e) {
            log.error("save User error - {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean editUser(UserPayload payload) {
        try {
            User user = new User();
            user.setId(payload.getId());
            user.setFullName(payload.getFullName());
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user.setUsername(payload.getUsername());
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            System.out.println(user.toString() + "=keldi");
            User user1 = userRepository.save(user);
            if (user1 != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("save User error - {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean saveAdmin(Long id) {
        try {
//            Admin admin=new Admin();
            User user = userRepository.getById(id);
//            admin.setId(user.getId());
//            admin.setAdmin(true);
//            admin.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
//            admin.setUsername(user.getUsername());
//            admin.setFullName(user.getFullName());
//            admin.setPassword(user.getPassword());
//            adminRepository.save(admin);
//            user.setId(id);
            user.setAdmin(true);
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_ADMIN")));
            System.out.println(user.toString() + "=keldi");
            User user1 = userRepository.save(user);
            if (user1 != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("save User error - {}", e.getMessage());
        }
        return false;
    }

    @Override
    public boolean editAdmin(Long id) {
        try {
            User user = userRepository.getById(id);
            user.setAdmin(false);
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            System.out.println(user.toString() + "=keldi");
            User user1 = userRepository.save(user);
            if (user1 != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("save User error - {}", e.getMessage());
        }
        return false;
    }

    @Override
    public ResponseEntity<?> getAllUser() {
        try {
            return ResponseEntity.ok(userRepository.findAll());
        } catch (Exception e) {
            log.error("save User error - {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getGroupsIdUsers(Long groupId) {
        try {
            return ResponseEntity.ok(userRepository.getByGroupIdUsers(groupId));
        } catch (Exception e) {
            log.error("save User error - {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public User edit(Long id, UserPayload payload) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        user.setFullName(payload.getFullName());
        user.setPassword(passwordEncoder.encode(payload.getPassword()));
        user.setUsername(payload.getUsername());
        return user;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("error deleteById User");
            return false;
        }
    }

}
