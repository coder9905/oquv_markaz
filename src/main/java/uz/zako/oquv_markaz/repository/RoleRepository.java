package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Role;
import uz.zako.oquv_markaz.entity.User;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);


}
