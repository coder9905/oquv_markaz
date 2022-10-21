package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Groups;
import uz.zako.oquv_markaz.payload.EmployePayload;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {

    @Query(nativeQuery = true,value = "delete from groups g join groups_subject gs on g.id = gs.groups_id join groups g2 on g2.id = gs.groups_id where gs.subject_id=?1")
    Boolean deleteGroupsSubjectId(Long id);

    @Query(nativeQuery = true, value ="select g.* from groups g join groups_subject gs on g.id = gs.groups_id join groups g2 on g2.id = gs.groups_id where gs.subject_id=?1")
    Page<Groups> getGroupsSubjectId(Pageable pageable,Long subjectId);

    @Query(nativeQuery = true, value ="select g.* from groups g join groups_employees ge on g.id = ge.groups_id where ge.employees_id=?1")
    Page<Groups> getGroupsEmployesId(Pageable pageable,Long id);

    @Query(nativeQuery = true, value = "delete from Groups g where g.subject_id=?1")
    Boolean deleteGroupsEmployeeId(Long id);

    @Query(nativeQuery = true, value = "select g.* from groups g inner join groups_subject gs on g.id = gs.groups_id inner join subject s on gs.subject_id = s.id inner join center_branches cb on s.center_branches_id = cb.id where cb.id=?1")
    List<Groups> getAllGroupCenterBranchesId(Long id);



}
