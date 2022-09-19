package uz.zako.oquv_markaz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Cource;
import uz.zako.oquv_markaz.payload.CourcesPayload;

@Repository
public interface CourcesRepository extends JpaRepository<Cource, Long> {

    Cource getById(Long id);

    @Query("select new uz.zako.oquv_markaz.payload.CourcesPayload(c.id,c.name,c.price,c.title,c.body,c.img.hashId) from Cource c")
    Page<CourcesPayload> findAllByPage(Pageable pageable);

}
