package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.payload.UserPayload;

public interface UserService {

    boolean save(UserPayload payload);

    boolean saveAdmin(UserPayload payload);

    User edit(Long id, UserPayload payload);

    ResponseEntity<?> getAllUser();

    boolean editUser(UserPayload payload);

    boolean editAdmin(Long id);

    boolean deleteById(Long id);
}
