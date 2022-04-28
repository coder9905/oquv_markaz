package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Cource;
import uz.zako.oquv_markaz.entity.Groups;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.GroupPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CourcesRepository;
import uz.zako.oquv_markaz.repository.GroupsRepository;
import uz.zako.oquv_markaz.service.GroupsService;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupsServiceImpl implements GroupsService {

    private final GroupsRepository groupsRepository;
    private final AttachmentRepository attachmentRepository;
    private final CourcesRepository courcesRepository;

    @Override
    public ResponseEntity<?> addGroups(GroupPayload payload){
        try {
            Groups groups=new Groups();
            groups.setName(payload.getName());
            groups.setCource(courcesRepository.getById(payload.getCourceId()));
            return ResponseEntity.ok(groupsRepository.save(groups));
        }catch (Exception e){
            log.error("add groups error -> {}",e.getMessage());
            return new ResponseEntity(new Result(false,"error",null),HttpStatus.BAD_REQUEST);
        }
    }
    @Override
    public ResponseEntity<?> editGroups(GroupPayload payload){
        try {
            Groups groups= groupsRepository.getById(payload.getId());
            groups.setId(payload.getId());
            groups.setName(payload.getName());
            groups.setCource(courcesRepository.getById(payload.getCourceId()));
            return ResponseEntity.ok(groupsRepository.save(groups));
        }catch (Exception e){
            log.error("add groups error -> {}",e.getMessage());
            return new ResponseEntity(new Result(false,"error",null),HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllGroups(){
        try {
            return ResponseEntity.ok(groupsRepository.findAll());
        }catch (Exception e){
            log.error("add groups error -> {}",e.getMessage());
            return new ResponseEntity(new Result(false,"error",null),HttpStatus.BAD_REQUEST);
        }
    }

}
