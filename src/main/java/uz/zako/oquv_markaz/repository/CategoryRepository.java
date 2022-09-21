package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(String name);


}
