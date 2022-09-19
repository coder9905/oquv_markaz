package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Admin;
import uz.zako.oquv_markaz.entity.User;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
}
