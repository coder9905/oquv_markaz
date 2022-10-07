package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;

public interface CenterBranchesService {
    ResponseEntity<?> save(Long trainingCenterId, CenterBranchesPayload payload);

    ResponseEntity<?> delete(Long id);

    ResponseEntity<?> edit(CenterBranchesPayload payload, Long id);

    ResponseEntity<?> getOne(Long id);

    ResponseEntity<?> getAll();

    ResponseEntity<?> getCenterBranchesTrainingId(int page, int size,Long id);

    ResponseEntity<?> getCenterBranchesTokenId();

    ResponseEntity<?> getAllPageCenterBranches(int page, int size);

    ResponseEntity<?> getAllCenterBranchesTrainingCenterId(Long id);
}
