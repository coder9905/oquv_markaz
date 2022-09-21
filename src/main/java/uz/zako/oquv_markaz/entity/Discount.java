package uz.zako.oquv_markaz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Discount extends AbstractEntity {

    private String time;

    private Long foiz;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private Groups groups;

}
