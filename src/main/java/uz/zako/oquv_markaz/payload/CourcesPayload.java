package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
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

    public CourcesPayload(String name, Long price, String title, String body, String img) {
        this.name = name;
        this.price = price;
        this.title = title;
        this.body = body;
        this.img = img;
    }

    public CourcesPayload() {
    }
}
