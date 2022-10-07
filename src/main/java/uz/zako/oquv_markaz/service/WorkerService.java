package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.WorkerPayload;

public interface WorkerService {
    ResponseEntity<?>  save(WorkerPayload payload);

    ResponseEntity<?> getOneWorkerId(Long id);

    ResponseEntity<?> getWorkerCenterBranchesId(int size, int page, Long id);

    ResponseEntity<?> getWorkerListCenterBranchesId(Long id);

    ResponseEntity<?> getAllWorker();

    ResponseEntity<?> editWorker(WorkerPayload payload);

    ResponseEntity<?> deleteWorker(Long workerId);
}
