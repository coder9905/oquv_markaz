package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.zako.oquv_markaz.entity.Categorys;
import uz.zako.oquv_markaz.entity.News;
import uz.zako.oquv_markaz.payload.NewsPayload;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {

    @Query(nativeQuery = true, value = "select * from news where categorys_id=:categoryId")
    List<News> findByAllNewsCategoryId(@Param("categoryId") Long id);

    @Query("select new uz.zako.oquv_markaz.payload.NewsPayload(n.id,n.title,n.body,n.img.hashId,n.categorys.id) from News n where n.categorys.id=:categoryId")
    Page<NewsPayload> findAllByPage(Pageable pageable, @Param("categoryId") Long id);

    void deleteById(Long id);

    News getById(Long id);

}
