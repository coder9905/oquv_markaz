package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.zako.oquv_markaz.entity.Attachment;
import uz.zako.oquv_markaz.entity.CenterBranches;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectPayload {

    private Long id;

    private String name;

    private String title;

    private String discription;

    private String hashId;

    private  Long centerBranchesId;

    public SubjectPayload(Long id, String name, String title, String discription, String hashId) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.discription = discription;
        this.hashId = hashId;
    }

}
