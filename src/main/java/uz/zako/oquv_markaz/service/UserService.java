package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.payload.UserPayload;

public interface UserService {

    ResponseEntity<?> saveUser(UserPayload payload);

    ResponseEntity<?> getOne(Long userId);

    ResponseEntity<?> getUserGroupId(int page, int size,Long groupId);

    ResponseEntity<?> getUsercenterBranchesId(int page, int size,Long id);

    ResponseEntity<?> getUsercenterBranchesAllId(Long id);

    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> getAllPageUsers(int page, int size);

    ResponseEntity<?> editUser(UserPayload payload);

    ResponseEntity<?> deleteUserId(Long id);

    ResponseEntity<?> deleteUserGroupId(Long id);

    ResponseEntity<?> deleteUserCenterBranchesId(Long id);

    ResponseEntity<?> saveCreator(UserPayload payload);

    ResponseEntity<?> saveMenegerAdmin(UserPayload payload);

    ResponseEntity<?> editMenegerAdmin(UserPayload payload);

    ResponseEntity<?> getAllRole();
}
