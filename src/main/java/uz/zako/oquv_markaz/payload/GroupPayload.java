package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GroupPayload {

    private Long id;
    private String name;
    private Long courceId;

    public GroupPayload() {
    }

    public GroupPayload(String name) {
        this.name = name;
    }
}
