package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Subject;
import uz.zako.oquv_markaz.payload.SubjectPayload;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query("select new uz.zako.oquv_markaz.payload.SubjectPayload(s.id,s.name,s.title,s.discription,s.img.hashId) from Subject s where s.centerBranches.id=?1")
    List<SubjectPayload> getCenterBranchesId(Long id);

    @Query(nativeQuery = true, value = "select * from subject s where s.center_branches_id=?1")
    Page<Subject> getAllSubjectCenterBranchesId(Pageable pageable, Long id);

    @Query(nativeQuery = true,value = "delete from Subject s where s.center_branches_id=:?1")
    Boolean deleteSubjcetByCenterBranchesId(Long id);


}
