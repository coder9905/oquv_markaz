package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Categorys;
import uz.zako.oquv_markaz.entity.News;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.payload.TeacherPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CategoryRepository;
import uz.zako.oquv_markaz.repository.NewsRepository;
import uz.zako.oquv_markaz.service.NewsService;

import java.util.Optional;

import static java.lang.String.format;

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
            Optional<Categorys> categorys = categoryRepository.findById(newsPayload.getCategoryId());
            if (!categorys.isPresent()){
                throw new RuntimeException(format("Category by this id = {%s} not found!", newsPayload.getCategoryId()));
            }
            news.setCategorys(categorys.get());
            news.setImg(attachmentRepository.findByHashId(newsPayload.getImg()));
            System.out.println("keldi news = "+news.toString());
            Long id = newsRepository.save(news).getId();
            return ResponseEntity.ok(new Result(true,"created",null));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> editNews(NewsPayload newsPayload) {
        try {
            News news = newsRepository.findById(newsPayload.getId()).get();
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
        System.out.println(categoryId);
        try {
            return (ResponseEntity.ok(newsRepository.findByAllNewsCategoryId(categoryId)));
        } catch (Exception e) {
            log.error("get news CategorId error -> {}", e.getMessage());
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
    public Page<NewsPayload> getPageNews(int page, int size) {

        PageRequest request = PageRequest.of(page, size);
        Page<NewsPayload> news = newsRepository.findAllByPage(request);

        System.out.println(news.getContent().size()+" ");

        for (int i = 0; i < news.getContent().size(); i++) {
            news.getContent().get(i).setImg(news.getContent().get(i).getImg());
        }
        return news;

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
