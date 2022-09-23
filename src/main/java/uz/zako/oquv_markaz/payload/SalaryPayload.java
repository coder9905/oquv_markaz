package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.User;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@Data
public class SalaryPayload {

    private Long id;

    private String title;

    private Long price;

    private String month;

    private Long employeeId;

    private Long userId;

}
