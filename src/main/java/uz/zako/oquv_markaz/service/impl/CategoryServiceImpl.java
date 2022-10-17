package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Category;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CategoryPayload;
import uz.zako.oquv_markaz.repository.CategoryRepository;
import uz.zako.oquv_markaz.service.CategoryService;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<?> addCategory(CategoryPayload payload) {

        try {
            Category categorys = new Category();
            categorys.setName(payload.getName());
            return ResponseEntity.ok(categoryRepository.save(categorys));
        } catch (Exception e) {
            log.error("add category error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> editCategory(CategoryPayload payload) {

        try {
            Category categorys = categoryRepository.findById(payload.getId()).get();
            categorys.setName(payload.getName());
            System.out.println(categorys);
            return ResponseEntity.ok(categoryRepository.save(categorys));
        } catch (Exception e) {
            log.error("add category error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getIdCategory(Long id) {
        try {
            return ResponseEntity.ok(categoryRepository.findById(id));
        } catch (Exception e) {
            log.error("getIdCategory error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error getIdCategory", null), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> getAllCategorys() {
        try {
            return ResponseEntity.ok(categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt")));
        } catch (Exception e) {
            log.error("getIdCategory error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error getIdCategory", null), HttpStatus.CONFLICT);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            System.out.println("delete ById ga keldi");
            categoryRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.error("getIdCategory error -> {}", e.getMessage());
//            return new ResponseEntity(new Result(false,"error getIdCategory",null),HttpStatus.CONFLICT);
            return false;
        }
    }

}
