package uz.zako.oquv_markaz.payload;

import lombok.Data;

@Data
public class LoginPayload {

    private String username;
    private String password;
}
