package uz.zako.oquv_markaz.payload;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherPayload {

    private Long id;
    private String fullName;
    private String phone;
    private String img;

    public TeacherPayload(String fullName, String phone, String img, Long groupsId) {
        this.fullName = fullName;
        this.phone = phone;
        this.img = img;
        this.groupsId = groupsId;
    }

    public TeacherPayload(Long id, String fullName, String phone, String img) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.img = img;
    }

    private Long groupsId;

}
