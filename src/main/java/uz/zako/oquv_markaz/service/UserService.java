package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.payload.UserPayload;

public interface UserService {

    ResponseEntity<?> saveUser(String hashId, UserPayload payload);

    ResponseEntity<?> getOne(Long userId);

    ResponseEntity<?> getUserGroupId(int page, int size,Long groupId);

    ResponseEntity<?> getUsercenterBranchesId(int page, int size,Long id);

    ResponseEntity<?> getAllUsers();

    ResponseEntity<?> editUser(String hashId, UserPayload payload);

    ResponseEntity<?> deleteUserId(Long id);

    ResponseEntity<?> deleteUserGroupId(Long id);

    ResponseEntity<?> deleteUserCenterBranchesId(Long id);

    ResponseEntity<?> saveCreator(String hashId, UserPayload payload);

    ResponseEntity<?> saveMenegerAdmin(String hashId, UserPayload payload);

    ResponseEntity<?> editMenegerAdmin(String hashId, UserPayload payload);

    ResponseEntity<?> getAllRole();
}
