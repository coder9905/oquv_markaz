package uz.zako.oquv_markaz.repository;

import uz.zako.oquv_markaz.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.zako.oquv_markaz.entity.RefreshToken;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findFirstByRefreshTokenOrderByCreatedAtDesc(String refreshToken);

}
