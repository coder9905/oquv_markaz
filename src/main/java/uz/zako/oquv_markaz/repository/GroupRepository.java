package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Groups;
import uz.zako.oquv_markaz.payload.EmployePayload;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {

    @Query(nativeQuery = true,value = "delete from Groups g where g.subject_id=?1")
    Boolean deleteGroupsSubjectId(Long id);

    @Query(nativeQuery = true, value ="select * from Groups g where g.subject_id=?1")
    List<Groups> getGroupsSubjectId(Long subjectId);

    @Query(nativeQuery = true, value ="select * from Groups g where g.employees_id=?1")
    List<Groups> getGroupsEmployesId(Long xodimId);

    @Query(nativeQuery = true, value = "delete from Groups g where g.subject_id=?1")
    Boolean deleteGroupsEmployeeId(Long id);


}
