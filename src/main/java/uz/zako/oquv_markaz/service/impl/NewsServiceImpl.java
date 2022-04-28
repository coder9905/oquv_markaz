package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.zako.oquv_markaz.entity.Attachment;
import uz.zako.oquv_markaz.entity.Categorys;
import uz.zako.oquv_markaz.entity.News;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CategoryPayload;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CategoryRepository;
import uz.zako.oquv_markaz.repository.NewsRepository;
import uz.zako.oquv_markaz.service.AttachmentService;
import uz.zako.oquv_markaz.service.NewsService;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public ResponseEntity<?> addNews(NewsPayload newsPayload) {
        try {
            News news = new News();
            news.setTitle(newsPayload.getTitle());
            news.setBody(newsPayload.getBody());
            news.setCategorys(categoryRepository.getById(newsPayload.getCategoryId()));
            news.setImg(attachmentRepository.findByHashId(newsPayload.getImg()));
            return ResponseEntity.ok(newsRepository.save(news));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> editNews(NewsPayload newsPayload) {
        try {
            News news = newsRepository.getById(newsPayload.getId());
            news.setId(newsPayload.getId());
            news.setTitle(newsPayload.getTitle());
            news.setBody(newsPayload.getBody());
            news.setCategorys(categoryRepository.getById(newsPayload.getCategoryId()));
            news.setImg(attachmentRepository.findByHashId(newsPayload.getImg()));
            return ResponseEntity.ok(newsRepository.save(news));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getCategoryIdNews(Long categoryId) {
        try {
            return (ResponseEntity<?>) newsRepository.findAllByCategorys_Id(categoryId);
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Page<NewsPayload> getPage(Long id, int page, int size) {

        PageRequest request = PageRequest.of(page, size);
        Page<NewsPayload> newsPayloads = newsRepository.findAllByPage(request, id);
        for (int i = 0; i < newsPayloads.getContent().size(); i++) {
            newsPayloads.getContent().get(i).setImg(newsPayloads.getContent().get(i).getImg());
        }
        return newsPayloads;

    }

    @Override
    public ResponseEntity<?> getNewsBody(Long id) {

        try {
            return ResponseEntity.ok(newsRepository.findById(id));
        } catch (Exception e) {
            log.error("getNewsBody");
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllNews() {

        try {
            return ResponseEntity.ok(newsRepository.findAll());
        } catch (Exception e) {
            log.error("getNewsBody");
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public boolean deleteById(Long id) {

        try {
            newsRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("getNewsBody");
//            return new ResponseEntity(new Result(false,"error",null),HttpStatus.INTERNAL_SERVER_ERROR);
            return false;
        }
    }


}
