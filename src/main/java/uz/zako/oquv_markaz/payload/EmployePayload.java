package uz.zako.oquv_markaz.payload;

import lombok.*;
import uz.zako.oquv_markaz.entity.Attachment;
import uz.zako.oquv_markaz.entity.CenterBranches;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployePayload {

    private Long id;

    private String fullName;

    private String username;

    private String password;

    private String adress;

    private boolean isTeacher;

    private String seniority;

    private List<String> phones;

    private Long monthly;

    private String hashId;

    private Long centerBranchesId;

    private String role;

    public EmployePayload(Long id, String fullName, String adress, boolean isTeacher, Long monthly, String seniority) {
        this.id = id;
        this.fullName = fullName;
        this.adress = adress;
        this.isTeacher = isTeacher;
        this.monthly=monthly;
        this.seniority = seniority;
    }

    public EmployePayload(String fullName, String adress, boolean isTeacher, String seniority, Long monthly) {
        this.fullName = fullName;
        this.adress = adress;
        this.isTeacher = isTeacher;
        this.seniority = seniority;
        this.monthly = monthly;
    }
}
