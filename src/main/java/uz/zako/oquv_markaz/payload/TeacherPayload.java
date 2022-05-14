package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TeacherPayload {

    private Long id;
    private String fullName;
    private String phone;
    private String img;

    public TeacherPayload(Long id, String fullName, String phone, String img) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.img = img;
    }

    public TeacherPayload() {
    }

    private Long groupsId;

}
