package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.zako.oquv_markaz.entity.Cource;

import java.util.Optional;

public interface CourcesRepository extends JpaRepository<Cource,Long> {

//    Cource findById(Long id);

}
