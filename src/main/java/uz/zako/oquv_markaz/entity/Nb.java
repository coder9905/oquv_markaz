package uz.zako.oquv_markaz.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Nb extends AbstractEntity {


    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> user;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<User> users;

}