package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.TrainingCenter;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;

import java.util.List;
import java.util.Optional;

@Repository
public interface CenterBranchesRepository extends JpaRepository<CenterBranches, Long> {

    @Query(nativeQuery = true,value = "select * from center_branches c where c.training_center_id=?1")
    Page<CenterBranches> getTrainingCenterId(Pageable pageable,Long id);

    @Query(nativeQuery = true,value = "select * from center_branches c where c.training_center_id=?1")
    List<CenterBranches> getCenterBranchesTrainingCenterId(Long id);

    @Query(nativeQuery = true,value = "select * from center_branches c order by create_at desc")
    Page<CenterBranches> findAllByDesc(Pageable pageable);

    @Query(nativeQuery = true, value = "select cb.* from center_branches cb join users_center_branches ucb on cb.id = ucb.center_branches_id join users u on ucb.users_id = u.id where u.username=?1")
    CenterBranches getCenterBranchesUserToken(String username);

    @Query("select new uz.zako.oquv_markaz.payload.CenterBranchesPayload(tc.id,tc.name, tc.workingTime) from CenterBranches tc")
    Page<CenterBranchesPayload> getAllCenterBranches(Pageable pageable);

}
