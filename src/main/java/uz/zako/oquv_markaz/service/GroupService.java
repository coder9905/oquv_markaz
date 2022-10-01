package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.GroupPayload;

public interface GroupService {
    ResponseEntity<?>  save(String hashId, GroupPayload payload);

    ResponseEntity<?> getOne(Long id);

    ResponseEntity<?> getAllGroups(int page, int size);

    ResponseEntity<?> getGroupsSubjectId(int page, int size,Long subjectId);

    ResponseEntity<?> getGroupsEmployesId(int page,int size,Long employesId);

    ResponseEntity<?> editGroups(GroupPayload payload, String hashId);

    ResponseEntity<?> deleteGroup(Long groupId);

    ResponseEntity<?> deleteGroupSubjectId(Long subjectId);

    ResponseEntity<?> deleteGroupEmployeId(Long employeId);
}
