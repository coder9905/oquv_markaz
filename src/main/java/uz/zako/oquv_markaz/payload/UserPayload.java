package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserPayload {

    private Long id;

    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String adress;
    private boolean isAdmin;
    private String hashId;
    private Long groupId;
    private String role;
    private Long centerBranchesId;

    public UserPayload(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    public UserPayload() {
    }

    public UserPayload(Long id, String username, String fullName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
    }
}
