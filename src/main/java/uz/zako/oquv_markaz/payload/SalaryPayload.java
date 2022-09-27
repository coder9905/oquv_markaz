package uz.zako.oquv_markaz.payload;

import lombok.*;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class SalaryPayload {

    private Long id;

    private String title;

    private Long price;

    private String month;

    private Long employeeId;

    private Long userId;

}
