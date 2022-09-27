package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Admin;
import uz.zako.oquv_markaz.entity.TrainingCenter;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;

import java.util.List;
import java.util.Optional;

@Repository
public interface TrainingCenterRepository extends JpaRepository<TrainingCenter, Long> {


    @Query(nativeQuery = true,value = "select tc.* from training_center tc join center_branches cb on tc.id = cb.training_center_id join users_center_branches ucb on cb.id = ucb.center_branches_id join users u on ucb.users_id = u.id where u.username=?1")
    List<TrainingCenter> getTrainingCenterUserToken(String name);

    @Query("select new uz.zako.oquv_markaz.payload.TrainingCenterPayload(tc.id,tc.name,tc.phone, tc.workingTime,tc.createdAt,tc.updatedAt) from TrainingCenter tc")
    Page<TrainingCenterPayload> getAllTrainingCenter(Pageable pageable);


//    @Query("select new com.example.booksstory.payload.CategoryPayload(b.id, bt.name, bt.id, bt.lang) from Category b inner join b.categoryTranslates bt where bt.lang=?1")
//    public Page<CategoryPayload> getAllWithLang(Pageable pageable, LanguageEnum languageEnum);

}
