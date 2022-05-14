package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CourcesPayload {

    private Long id;
    private String name;
    private Long price;
    private String title;
    private String body;
    private String img;



    public CourcesPayload() {
    }
}
