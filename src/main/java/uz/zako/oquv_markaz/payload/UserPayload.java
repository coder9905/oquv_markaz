package uz.zako.oquv_markaz.payload;

import lombok.Data;

@Data
public class UserPayload {

    private Long id;

    private String username;
    private String password;
    private String fullName;

    public UserPayload() {
    }
}
