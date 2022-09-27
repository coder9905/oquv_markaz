package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.Phone;
import uz.zako.oquv_markaz.payload.CenterBranchesPayload;

import java.util.List;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {

}
