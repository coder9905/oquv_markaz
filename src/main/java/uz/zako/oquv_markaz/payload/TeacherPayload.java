package uz.zako.oquv_markaz.payload;

import lombok.Data;

@Data
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

    private Long groupsName;

}
