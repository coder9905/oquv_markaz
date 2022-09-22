package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.SubjectPayload;

public interface SubjcetService {
    ResponseEntity<?> saveSubjcet(String hashId, SubjectPayload payload);

    ResponseEntity<?> getOne(Long subjectId);

    ResponseEntity<?> getSubjectCenterBranchesId(Long cemterBranchesId);

    ResponseEntity<?> getAllSubject();

    ResponseEntity<?> editSubject(String hashId, SubjectPayload payload);

    ResponseEntity<?> deleteSubjectId(Long id);

    ResponseEntity<?> deleteSubjectCenterBranchesId(Long centerBranchesId);
}
