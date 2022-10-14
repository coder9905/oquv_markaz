package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Worker;
import uz.zako.oquv_markaz.payload.WorkerPayload;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

//    @Query("select new uz.zako.oquv_markaz.payload.WorkerPayload(e.id,e.fullName,e.adress,e.monthly) from Worker e where e.centerBranches.id=?1")
    @Query(nativeQuery = true,value = "select * from worker e where e.center_branches_id=?1")
    List<Worker> getCenterBranchesId(Long id);

//    @Query("select new uz.zako.oquv_markaz.payload.WorkerPayload(e.id,e.fullName,e.adress,e.monthly) from Worker e inner join Phone p where e.centerBranches.id=?1")
    @Query(nativeQuery = true,value = "select * from worker w where w.center_branches_id=?1")
    Page<Worker> getWorkerCenterBranchesId(Pageable pageable,Long id);

    @Query(nativeQuery = true,value = "select * from employee e where e.center_branches_id=?1")
    Page<Employee> getAllEmployeCenterBranchesId(Pageable pageable, Long id);

    @Query(nativeQuery = true,value = "delete from Employe e where e.center_branches_id=?1")
    Boolean deleteEmployeeByCenterBranchesId(Long id);


}
