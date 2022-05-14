package uz.zako.oquv_markaz.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.zako.oquv_markaz.payload.NewsPayload;

public interface NewsService {

    ResponseEntity<?> addNews(NewsPayload newsPayload);

    ResponseEntity<?> editNews(NewsPayload newsPayload);

    ResponseEntity<?> getCategoryIdNews(Long categoryId);

    Page<NewsPayload> getPage(Long id, int page, int size);

    ResponseEntity<?> getNewsBody(Long id);

    ResponseEntity<?> getAllNews();

    Page<NewsPayload> getPageNews(int page, int size);

    boolean deleteById(Long id);
}
