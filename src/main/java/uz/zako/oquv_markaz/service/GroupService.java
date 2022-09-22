package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.GroupPayload;

public interface GroupService {
    ResponseEntity<?>  save(String hashId, GroupPayload payload);

    ResponseEntity<?> getOne(Long id);

    ResponseEntity<?> getAllGroups();

    ResponseEntity<?> getGroupsSubjectId(Long subjectId);

    ResponseEntity<?> getGroupsEmployesId(Long employesId);

    ResponseEntity<?> editGroups(GroupPayload payload, String hashId);

    ResponseEntity<?> deleteGroup(Long groupId);

    ResponseEntity<?> deleteGroupSubjectId(Long subjectId);

    ResponseEntity<?> deleteGroupEmployeId(Long employeId);
}
