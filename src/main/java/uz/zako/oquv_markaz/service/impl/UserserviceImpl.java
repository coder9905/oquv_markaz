package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.Phone;
import uz.zako.oquv_markaz.entity.Role;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserserviceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final CenterBranchesRepository centerBranchesRepository;
    private final AttachmentRepository attachmentRepository;
    private final PhoneRepository phoneRepository;
    private final SecurityUtils securityUtils;

    @Override
    public ResponseEntity<?> saveUser(String hashId, UserPayload payload) {
        try {
            User user = new User();
            User user1 = new User();
            user.setUsername(payload.getUsername());
            user.setFullName(payload.getFullName());
            List<Phone> phones = new ArrayList<>();

            for (int i = 0; i < payload.getPhone().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhone().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            user.setPhones(phones);
            user.setAdress(payload.getAdress());
            user.setRoles(Arrays.asList(roleRepository.findByName(payload.getRole())));
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user.setImg(attachmentRepository.findByHashId1(hashId).orElseThrow(() -> new ResourceNotFoundException("attachment not found")));
            user1 = userRepository.save(user);
            if (user1 != null) {
                return ResponseEntity.ok(new Result(true, "save succesfull", user));
            }
        return new ResponseEntity(new Result(false, "save not succesfull", null), HttpStatus.BAD_REQUEST);
    } catch(Exception e)
    {
        log.error("error user", e.getMessage());
        return new ResponseEntity(new Result(false, "save not succesfull", null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

    @Override
    public ResponseEntity<?> getOne(Long userId) {
        try {
            User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user not found"));
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            User userToken = userRepository.findByUsername(username);
            if (user.getCenterBranches().get(0).getId() == userToken.getCenterBranches().get(0).getId()) {
                return ResponseEntity.ok(new Result(true, "get One User", user));
            }
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUserGroupId(int page, int size,Long groupId) {
        try {
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            User userToken = userRepository.findByUsername(username);
            Page<UserPayload> users = userRepository.getByUsersGroupId(PageRequest.of(page, size),groupId, userToken.getCenterBranches().get(0).getId());
            if (users != null) {
                return ResponseEntity.ok(new Result(true, "getUserGroupId", users));
            }
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUsercenterBranchesId(int page, int size,Long id) {
        try {
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            User userToken = userRepository.findByUsername(username);
            if (userToken.getCenterBranches().get(0).getId() == id) {
                Page<User> users = userRepository.getBycenterBranchesIdUsers(PageRequest.of(page,size),id);
                return ResponseEntity.ok(new Result(true, "getUsercenterBranchesId", users));
            }
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUsercenterBranchesAllId(Long id) {
        try {
            String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
            User userToken = userRepository.findByUsername(username);
            if (userToken.getCenterBranches().get(0).getId() == id) {
                List<User> users = userRepository.getBycenterBranchesAllIdUsers(id);
                return ResponseEntity.ok(new Result(true, "getUsercenterBranchesId", users));
            }
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            if (users != null) {
                return ResponseEntity.ok(new Result(true, "getUsercenterBranchesId", users));
            }
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllPageUsers(int page, int size) {
        try {
            Page<User> users = userRepository.findAll(PageRequest.of(page,size,Sort.by(Sort.Direction.DESC, "createdAt")));
            if (users != null) {
                return ResponseEntity.ok(new Result(true, "getUsercenterBranchesId", users));
            }
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "error user", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editUser(String hashId, UserPayload payload) {
        try {
            User user = userRepository.findById(payload.getId()).orElseThrow(() -> new ResourceNotFoundException("user not found"));
            user.setUsername(payload.getUsername());
            user.setFullName(payload.getFullName());
            List<Phone> phones = new ArrayList<>();

            for (int i = 0; i < payload.getPhone().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhone().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            user.setPhones(phones);
            user.setAdress(payload.getAdress());
            user.setAdmin(payload.isAdmin());
            if (payload.getRole() != null) {
                user.setRoles(Arrays.asList(roleRepository.findByName(payload.getRole())));
            } else {
                user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            }
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user.setImg(attachmentRepository.findByHashId1(hashId).orElseThrow(() -> new ResourceNotFoundException("attachment not found")));
            user.setCenterBranches(Arrays.asList(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(() -> new ResourceNotFoundException("centerBranches not found"))));
            user = userRepository.save(user);
            if (user != null) {
                return ResponseEntity.ok(new Result(true, "edit succesfull", user));
            }
            return new ResponseEntity(new Result(false, "edit not succesfull", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "edit not succesfull", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserId(Long id) {
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "delete not succesfull", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserGroupId(Long id) {
        try {
            userRepository.deleteGroupIdUser(id);
            return ResponseEntity.ok("delete succesfull");
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "delete not succesfull", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserCenterBranchesId(Long id) {
        try {
            userRepository.deleteCenterBranchesIdUser(id);
            return ResponseEntity.ok("delete succesfull");
        } catch (Exception e) {
            log.error("error user", e.getMessage());
            return new ResponseEntity(new Result(false, "delete not succesfull", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> saveCreator(UserPayload payload) {
        try {
            User user = new User();
            user.setUsername(payload.getUsername());
            user.setFullName(payload.getFullName());
            List<Phone> phones = new ArrayList<>();

            for (int i = 0; i < payload.getPhone().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhone().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            user.setAdress(payload.getAdress());
            user.setAdmin(false);
            if (payload.getHashId() != null) {
                user.setImg(attachmentRepository.findByHashId1(payload.getHashId()).get());
            }
            user.setPhones(phones);
            user.setCenterBranches(Arrays.asList(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(() -> new ResourceNotFoundException("centerBranches not found"))));
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user = userRepository.save(user);
            if (user != null) {
                return ResponseEntity.ok(Result.ok(user));
            }
            return new ResponseEntity(Result.error("error save not succesfull creator"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("save cretor error", e.getMessage());
            return new ResponseEntity(Result.error("error save not succesfull creator"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> saveMenegerAdmin(UserPayload payload){
        try {
            User user=new User();
            user.setUsername(payload.getUsername());
            user.setFullName(payload.getFullName());
            List<Phone> phones = new ArrayList<>();

            for (int i = 0; i < payload.getPhone().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhone().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            user.setPhones(phones);
            user.setAdress(payload.getAdress());
            user.setRoles(Arrays.asList(roleRepository.findByName(payload.getRole())));
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            if (payload.getHashId() != null) {
                user.setImg(attachmentRepository.findByHashId1(payload.getHashId()).orElseThrow(() -> new ResourceNotFoundException("attachment not found")));
            }
            user=userRepository.save(user);
            if (user != null){
                return ResponseEntity.ok(Result.ok(user));
            }
            return new ResponseEntity(Result.error("error saveMenegerAdmin not succesfull"), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            log.error("saveMenegerAdmin error", e.getMessage());
            return new ResponseEntity(Result.error("error saveMenegerAdmin not succesfull"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editMenegerAdmin(UserPayload payload){
        try {
            User user=userRepository.findById(payload.getId()).get();
            user.setUsername(payload.getUsername());
            user.setFullName(payload.getFullName());
            List<Phone> phones = new ArrayList<>();

            for (int i = 0; i < payload.getPhone().size(); i++) {
                Phone phone = new Phone();
                phone.setPhone(payload.getPhone().get(i));
                phone = phoneRepository.save(phone);
                phones.add(phone);
            }
            user.setPhones(phones);
            user.setAdress(payload.getAdress());
            user.setRoles(Arrays.asList(roleRepository.findByName(payload.getRole())));
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            if (payload.getHashId() != null) {
                user.setImg(attachmentRepository.findByHashId1(payload.getHashId()).orElseThrow(() -> new ResourceNotFoundException("attachment not found")));
            }
            user=userRepository.save(user);
            if (user != null){
                return ResponseEntity.ok(Result.ok(user));
            }
            return new ResponseEntity(Result.error("error saveMenegerAdmin not succesfull"), HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e) {
            log.error("saveMenegerAdmin error", e.getMessage());
            return new ResponseEntity(Result.error("error saveMenegerAdmin not succesfull"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllRole(){
        try {
            List<Role> roles=new ArrayList<>();
            Role role=roleRepository.findByName("MENEGER");
            roles.add(role);
            Role role1=roleRepository.findByName("ADMIN");
            roles.add(role1);
            Role role2=roleRepository.findByName("USER");
            roles.add(role2);
            if (roles.size()>=1){
                return ResponseEntity.ok(Result.ok(roles));
            }
            return new ResponseEntity(Result.error("error getAllRole"), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            log.error("getAllRole error", e.getMessage());
            return new ResponseEntity(Result.error("error getAllRole"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
