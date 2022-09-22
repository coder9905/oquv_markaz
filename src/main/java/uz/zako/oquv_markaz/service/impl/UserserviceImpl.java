package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.service.UserService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserserviceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final AdminRepository adminRepository;
    private final GroupRepository groupRepository;
    private final CenterBranchesRepository centerBranchesRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public ResponseEntity<?> saveUser(String hashId, UserPayload payload){
        try {
            User user=new User();
            user.setUsername(payload.getUsername());
            user.setFullName(payload.getFullName());
            user.setPhone(payload.getPhone());
            user.setAdress(payload.getAdress());
            user.setAdmin(payload.isAdmin());
            user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user.setImg(attachmentRepository.findByHashId1(hashId).orElseThrow(()->new ResourceNotFoundException("attachment not found")));
            user.setCenterBranches(Arrays.asList(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(()->new ResourceNotFoundException("centerBranches not found"))));
            user=userRepository.save(user);
            if (user != null){
                return ResponseEntity.ok(new Result(true, "save succesfull", user));
            }
            return new ResponseEntity(new Result(false,"save not succesfull",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"save not succesfull",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long userId){
        try {
            User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("user not found"));
            return ResponseEntity.ok(new Result(true, "get One User", user));
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"error user",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUserGroupId(Long groupId){
        try {
            List<User> users=userRepository.getByGroupIdUsers(groupId);
            if (users != null){
                return ResponseEntity.ok(new Result(true, "getUserGroupId", users));
            }
            return new ResponseEntity(new Result(false,"error user",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"error user",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getUsercenterBranchesId(Long id){
        try {
            List<User> users=userRepository.getBycenterBranchesIdUsers(id);
            if (users != null){
                return ResponseEntity.ok(new Result(true, "getUsercenterBranchesId", users));
            }
            return new ResponseEntity(new Result(false,"error user",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"error user",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllUsers(){
        try {
            List<User> users=userRepository.findAll();
            if (users != null){
                return ResponseEntity.ok(new Result(true, "getUsercenterBranchesId", users));
            }
            return new ResponseEntity(new Result(false,"error user",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"error user",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editUser(String hashId, UserPayload payload){
        try {
            User user=userRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("user not found"));
            user.setUsername(payload.getUsername());
            user.setFullName(payload.getFullName());
            user.setPhone(payload.getPhone());
            user.setAdress(payload.getAdress());
            user.setAdmin(payload.isAdmin());
            if (payload.getRole() != null){
                user.setRoles(Arrays.asList(roleRepository.findByName(payload.getRole())));
            }else {
                user.setRoles(Arrays.asList(roleRepository.findByName("ROLE_USER")));
            }
            user.setPassword(passwordEncoder.encode(payload.getPassword()));
            user.setImg(attachmentRepository.findByHashId1(hashId).orElseThrow(()->new ResourceNotFoundException("attachment not found")));
            user.setCenterBranches(Arrays.asList(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(()->new ResourceNotFoundException("centerBranches not found"))));
            user=userRepository.save(user);
            if (user != null){
                return ResponseEntity.ok(new Result(true, "edit succesfull", user));
            }
            return new ResponseEntity(new Result(false,"edit not succesfull",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"edit not succesfull",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserId(Long id){
        try {
            userRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"delete not succesfull",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserGroupId(Long id){
        try {
            userRepository.deleteGroupIdUser(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"delete not succesfull",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteUserCenterBranchesId(Long id){
        try {
            userRepository.deleteCenterBranchesIdUser(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error user",e.getMessage());
            return new ResponseEntity(new Result(false,"delete not succesfull",null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }





}
