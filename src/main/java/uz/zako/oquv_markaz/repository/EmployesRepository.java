package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.EmployePayload;

import java.util.List;

@Repository
public interface EmployesRepository extends JpaRepository<Employee, Long> {

    @Query("select new uz.zako.oquv_markaz.payload.EmployePayload(e.id,e.fullName,e.adress,e.isTeacher,e.monthly,e.seniority) from Employee e where e.centerBranches.id=?1")
    List<EmployePayload> getCenterBranchesId(Long id);

    @Query(nativeQuery = true,value = "select * from employee e where e.center_branches_id=?1")
    Page<Employee> getAllEmployeCenterBranchesId(Pageable pageable, Long id);

    @Query(nativeQuery = true,value = "select * from employee e where e.center_branches_id=?1")
    List<Employee> getEmployeCenterBranchesAllId(Long id);

    @Query(nativeQuery = true,value = "delete from employee e where e.center_branches_id=?1")
    Boolean deleteEmployeeByCenterBranchesId(Long id);


}
