package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Cource;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CourcesPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CourcesRepository;
import uz.zako.oquv_markaz.service.CourcesService;

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
            cource.setTitle(courcesPayload.getTitle());
            cource.setPrice(courcesPayload.getPrice());
            cource.setBody(courcesPayload.getBody());
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
            Cource cource = courcesRepository.findById(courcesPayload.getId()).get();
            cource.setName(courcesPayload.getName());
            cource.setTitle(courcesPayload.getTitle());
            cource.setTitle(courcesPayload.getTitle());
            cource.setPrice(courcesPayload.getPrice());
            cource.setBody(courcesPayload.getBody());
            cource.setImg(attachmentRepository.findByHashId(courcesPayload.getImg()));
            return ResponseEntity.ok(courcesRepository.save(cource));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Page<CourcesPayload> getPageCource(int page, int size) {

        PageRequest request = PageRequest.of(page, size);
        Page<CourcesPayload> cource = courcesRepository.findAllByPage(request);

        System.out.println(cource.getContent().size());

        for (int i = 0; i < cource.getContent().size(); i++) {
            cource.getContent().get(i).setImg(cource.getContent().get(i).getImg());
        }
        return cource;

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
