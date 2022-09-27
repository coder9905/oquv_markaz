package uz.zako.oquv_markaz.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewsPayload {

    private Long id;
    private String title;
    private String body;
    private String img;
    private Long categoryId;

    public NewsPayload(String title, String body, String img, Long categoryId) {
        this.title = title;
        this.body = body;
        this.img = img;
        this.categoryId = categoryId;
    }
}
