package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Subject;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.SubjectPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CenterBranchesRepository;
import uz.zako.oquv_markaz.repository.SubjectRepository;
import uz.zako.oquv_markaz.service.SubjcetService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjcetServiceImpl implements SubjcetService {

    private final SubjectRepository subjectRepository;
    private final CenterBranchesRepository centerBranchesRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public ResponseEntity<?> saveSubjcet(SubjectPayload payload){
        try {
            Subject subject=new Subject();
            subject.setName(payload.getName());
            subject.setTitle(payload.getTitle());
            subject.setDiscription(payload.getDiscription());
            if (payload.getHashId() != null) {
                subject.setImg(attachmentRepository.findByHashId1(payload.getHashId()).orElseThrow(() -> new ResourceNotFoundException("attachment not found")));
            }
            subject.setCenterBranches(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(()->new ResourceNotFoundException("centerBranches not found")));
            subject=subjectRepository.save(subject);
            if (subject != null){
                return ResponseEntity.ok(new Result(true, "save succesfull",subject));
            }
            return new ResponseEntity(new Result(false,"error save subject", null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("save Subjcet error",e.getMessage());
            return new ResponseEntity(new Result(false,"no save succesfull", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long subjectId){
        try {
            Subject subject=subjectRepository.findById(subjectId).orElseThrow(()->new ResourceNotFoundException("subject not found"));
            return ResponseEntity.ok(new Result(true,"getOneSubject",subject));
        }catch (Exception e){
            log.error("save Subjcet error",e.getMessage());
            return new ResponseEntity(new Result(false,"no save succesfull", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getSubjectCenterBranchesId(int page, int size,Long centerBranchesId){
        try {
            Page<Subject> subjects=subjectRepository.getAllSubjectCenterBranchesId(PageRequest.of(page,size),centerBranchesId);
            if (subjects != null){
                return ResponseEntity.ok(new Result(true,"getSubjectCenterBranchesId",subjects));
            }
            return new ResponseEntity(new Result(false,"getOne error succesfull", null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("getOne Subjcet error",e.getMessage());
            return new ResponseEntity(new Result(false,"getOne error succesfull", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllSubject(int page, int size){
        try {
            Page<Subject> subjects=subjectRepository.findAll(PageRequest.of(page, size));
            if (subjects != null){
                return ResponseEntity.ok(new Result(true,"getAll Subject",subjects));
            }
            return new ResponseEntity(new Result(false,"getAll error succesfull", null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("getAll Subjcet error",e.getMessage());
            return new ResponseEntity(new Result(false,"getAll error succesfull", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> editSubject(SubjectPayload payload){
        try {
            Subject subject=subjectRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("subject not found"));
            subject.setName(payload.getName());
            subject.setTitle(payload.getTitle());
            subject.setDiscription(payload.getDiscription());
            if (payload.getHashId() != null) {
                subject.setImg(attachmentRepository.findByHashId1(payload.getHashId()).orElseThrow(() -> new ResourceNotFoundException("attachment not found")));
            }
            subject.setCenterBranches(centerBranchesRepository.findById(payload.getCenterBranchesId()).orElseThrow(()->new ResourceNotFoundException("centerBranches not found")));
            subject=subjectRepository.save(subject);
            if (subject != null){
                return ResponseEntity.ok(new Result(true, "edit succesfull",subject));
            }
            return new ResponseEntity(new Result(false,"edit save subject", null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("edit Subjcet error",e.getMessage());
            return new ResponseEntity(new Result(false,"edit error succesfull", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteSubjectId(Long id){
        try {
            subjectRepository.deleteById(id);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("edit Subjcet error",e.getMessage());
            return new ResponseEntity(new Result(false,"edit error succesfull", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteSubjectCenterBranchesId(Long centerBranchesId){
        try {
            if (subjectRepository.deleteSubjcetByCenterBranchesId(centerBranchesId)){
                return ResponseEntity.ok("delete succesfull");
            }
            return new ResponseEntity(new Result(false,"delete error succesfull", null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("delete Subjcet error",e.getMessage());
            return new ResponseEntity(new Result(false,"delete error succesfull", null),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
