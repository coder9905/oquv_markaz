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
public class Nb extends AbstractEntity {


    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private User users;

}
