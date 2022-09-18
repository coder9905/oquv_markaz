package uz.zako.oquv_markaz.entity;

import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
public class RefreshToken extends AbstractEntity {

    private String refreshToken;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Date expiredTime;

}


