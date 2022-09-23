package uz.zako.oquv_markaz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.Block;
import uz.zako.oquv_markaz.entity.Payment;

import java.util.List;

@Repository
public interface BlockRepository extends JpaRepository<Block, Long> {


}
