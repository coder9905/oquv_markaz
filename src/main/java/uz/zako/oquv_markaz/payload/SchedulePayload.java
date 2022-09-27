package uz.zako.oquv_markaz.payload;

import lombok.*;
import uz.zako.oquv_markaz.entity.Employee;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class SchedulePayload {

    private Long id;

    private String week;

    private String firstTime;

    private String finishTime;

    private Long weekId;

    private Long employeeId;
}
