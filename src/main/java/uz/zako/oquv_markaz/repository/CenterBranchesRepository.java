package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.TrainingCenter;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;

import java.util.List;

@Repository
public interface CenterBranchesRepository extends JpaRepository<CenterBranches, Long> {

    @Query("select new uz.zako.oquv_markaz.payload.CenterBranchesPayload(c.id,c.name,c.phone) from CenterBranches c where c.training_center.id=?1")
    List<CenterBranchesPayload> getTrainingCenterId(Long id);


}
