package uz.zako.oquv_markaz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Outputs extends AbstractEntity {

    private String title;

    @Column(columnDefinition = "TEXT")
    private String discription;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private User user;

}
