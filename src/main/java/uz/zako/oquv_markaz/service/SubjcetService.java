package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.SubjectPayload;

public interface SubjcetService {
    ResponseEntity<?> saveSubjcet(SubjectPayload payload);

    ResponseEntity<?> getOne(Long subjectId);

    ResponseEntity<?> getSubjectCenterBranchesId(int page, int size,Long cemterBranchesId);

    ResponseEntity<?> getAllSubject(int page, int size);

    ResponseEntity<?> getAllSubject();

    ResponseEntity<?> editSubject(SubjectPayload payload);

    ResponseEntity<?> deleteSubjectId(Long id);

    ResponseEntity<?> deleteSubjectCenterBranchesId(Long centerBranchesId);
}
