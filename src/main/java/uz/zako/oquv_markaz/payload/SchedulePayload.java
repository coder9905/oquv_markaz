package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.zako.oquv_markaz.entity.Employee;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@Data
public class SchedulePayload {

    private Long id;

    private String week;

    private String firstTime;

    private String finishTime;

    private Long weekId;

    private Long employeeId;
}
