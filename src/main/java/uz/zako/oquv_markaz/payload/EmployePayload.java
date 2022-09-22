package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import uz.zako.oquv_markaz.entity.Attachment;
import uz.zako.oquv_markaz.entity.CenterBranches;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@Getter
@Setter
@Data
public class EmployePayload {

    private Long id;

    private String fullName;

    private String phone;

    private String adress;

    private boolean isTeacher;

    private String position;

    private String seniority;

    private Long monthly;

    private String hashId;

    private Long centerBranchesId;

    public EmployePayload(Long id, String fullName, String phone, String adress, boolean isTeacher, Long monthly, String position, String seniority) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.adress = adress;
        this.isTeacher = isTeacher;
        this.monthly=monthly;
        this.position = position;
        this.seniority = seniority;
    }

}
