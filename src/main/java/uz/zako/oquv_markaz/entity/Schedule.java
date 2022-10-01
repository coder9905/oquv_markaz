package uz.zako.oquv_markaz.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class Schedule extends AbstractEntity {

    private String week;

    private String firstTime;

    private String finishTime;

    @ManyToMany
    private List<Week> weeks;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Employee employee;

}
