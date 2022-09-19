package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.GroupPayload;

public interface GroupsService {

    ResponseEntity<?> addGroups(GroupPayload payload);

    ResponseEntity<?> getAllGroups();

    ResponseEntity<?> editGroups(GroupPayload payload);
}
