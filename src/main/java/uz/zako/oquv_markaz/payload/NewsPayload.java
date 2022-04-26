package uz.zako.oquv_markaz.payload;

import lombok.Data;

@Data
public class NewsPayload {

    private Long id;
    private String title;
    private String body;
    private String img;
    private Long categoryId;

    public NewsPayload() {
    }

    public NewsPayload(Long id, String title, String body, String img, Long categoryId) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.img = img;
        this.categoryId = categoryId;
    }

}
