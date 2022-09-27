package uz.zako.oquv_markaz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.abstractEntity.AbstractEntity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class TrainingCenter extends AbstractEntity {

    private String name;

    private String workingTime;

    private boolean block;

    @ManyToMany
    private List<Phone> phones;

}
