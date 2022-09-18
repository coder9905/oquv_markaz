package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthTokenPayload {

    private String access_token;
    private String refresh_token;
    private String username;
    private boolean succes;

}
