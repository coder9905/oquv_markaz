package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.EmployePayload;

public interface EmployeeService {
    ResponseEntity<?> save(EmployePayload payload);

    ResponseEntity<?> saveMenegerAdmin(EmployePayload payload);

    ResponseEntity<?> getOne(Long id);

    ResponseEntity<?> getEmployeCenterBranchesId(int size, int page,Long id);

    ResponseEntity<?> getEmployeCenterBranchesAllId(Long id);

    ResponseEntity<?> getAllEmployee();

    ResponseEntity<?> editEmploye(EmployePayload payload);

    ResponseEntity<?> deleteEmploye(Long employeId);

    ResponseEntity<?> deleteEmployeCenterBranchesId(Long centerBranchesId);
}
