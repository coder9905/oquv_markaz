package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.EmployePayload;

public interface EmployeeService {
    ResponseEntity<?> save(EmployePayload payload, String hashId);

    ResponseEntity<?> saveMenegerAdmin(String hashId, EmployePayload payload);

    ResponseEntity<?> getOne(Long id);

    ResponseEntity<?> getEmployeCenterBranchesId(int size, int page,Long id);

    ResponseEntity<?> getAllEmployee();

    ResponseEntity<?> editEmploye(EmployePayload payload,String hashId);

    ResponseEntity<?> deleteEmploye(Long employeId);

    ResponseEntity<?> deleteEmployeCenterBranchesId(Long centerBranchesId);
}
