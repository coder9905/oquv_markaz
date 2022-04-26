package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Cource;
import uz.zako.oquv_markaz.entity.News;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CourcesPayload;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CategoryRepository;
import uz.zako.oquv_markaz.repository.CourcesRepository;
import uz.zako.oquv_markaz.repository.NewsRepository;
import uz.zako.oquv_markaz.service.CourcesService;
import uz.zako.oquv_markaz.service.NewsService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CourcesServiceImpl implements CourcesService {

    private final CourcesRepository courcesRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public ResponseEntity<?> addCources(CourcesPayload courcesPayload) {
        try {
            Cource cource = new Cource();
            cource.setName(courcesPayload.getName());
            cource.setTitle(cource.getTitle());
            cource.setPrice(courcesPayload.getPrice());
            courcesPayload.setBody(courcesPayload.getBody());
            cource.setImg(attachmentRepository.findByHashId(courcesPayload.getImg()));
            return ResponseEntity.ok(courcesRepository.save(cource));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> editCources(CourcesPayload courcesPayload) {
        try {
            Cource cource = new Cource();
            cource.setId(courcesPayload.getId());
            cource.setName(courcesPayload.getName());
            cource.setTitle(cource.getTitle());
            cource.setPrice(courcesPayload.getPrice());
            courcesPayload.setBody(courcesPayload.getBody());
            cource.setImg(attachmentRepository.findByHashId(courcesPayload.getImg()));
            return ResponseEntity.ok(courcesRepository.save(cource));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllCources() {
        try {
            return ResponseEntity.ok(courcesRepository.findAll());
        } catch (Exception e) {
            log.error("error getAllCources -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            courcesRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("error getAllCources -> {}", e.getMessage());
            return false;
        }
    }

}
