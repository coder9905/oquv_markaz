package uz.zako.oquv_markaz.payload;

import lombok.Data;

@Data
public class CourcesPayload {

    private Long id;
    private String name;
    private Long price;
    private String title;
    private String body;
    private String img;


}
