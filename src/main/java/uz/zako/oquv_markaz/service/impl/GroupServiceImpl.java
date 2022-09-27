package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Groups;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.GroupPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.service.EmployeeService;
import uz.zako.oquv_markaz.service.GroupService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final EmployesRepository employesRepository;
    private final SubjectRepository subjectRepository;
    private final GroupRepository groupRepository;
    private final AttachmentRepository attachmentRepository;

    @Override
    public ResponseEntity<?>  save(String hashId, GroupPayload payload){
        try {
            Groups groups=new Groups();
            groups.setName(payload.getName());
            groups.setPrice(payload.getPrice());
            groups.setTitle(payload.getTitle());
            groups.setDiscription(payload.getDiscription());
            groups.setDuration(payload.getDuration());
            groups.setCapacity(payload.getCapacity());
            groups.setImg(Arrays.asList(attachmentRepository.findByHashId1(hashId).orElseThrow(()->new ResourceNotFoundException("Attachment not found"))));
            groups.setEmployees(Arrays.asList(employesRepository.findById(payload.getEmployeesId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found"))));
            groups.setSubject(Arrays.asList(subjectRepository.findById(payload.getSubjectId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found"))));

            groups=groupRepository.save(groups);

            if (groups != null) {
                return ResponseEntity.ok("save succesfull");
            }

            return new ResponseEntity(new Result(false,"error save employes",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error groups",e.getMessage());
            return new ResponseEntity(new Result(false,"groups saqlashda hatolik",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long id){
        try {
            Groups groups=groupRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("group not found"));
            return ResponseEntity.ok(new Result(true,"getOneGroup",groups));
        }catch (Exception e){
            log.error("error group",e.getMessage());
            return new ResponseEntity(new Result(false,"get One group error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllGroups(){
        try {
            List<Groups> groupss=groupRepository.findAll();
            System.out.println(groupss.toString());
            if (groupss != null){
                return ResponseEntity.ok(new Result(true,"getAll Groups",groupss));
            }
            return new ResponseEntity(new Result(false,"getAll groups error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error groups",e.getMessage());
            return new ResponseEntity(new Result(false,"getAll groups error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getGroupsSubjectId(Long subjectId){
        try {
            List<Groups> groupPayloads=groupRepository.getGroupsSubjectId(subjectId);
            if (groupPayloads != null){
                return ResponseEntity.ok(new Result(true,"get getGroupsSubjectId",groupPayloads));
            }
            return new ResponseEntity(new Result(false,"getGroupsSubjectId employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error groups",e.getMessage());
            return new ResponseEntity(new Result(false,"getGroupsSubjectId employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getGroupsEmployesId(Long employesId){
        try {
            List<Groups> groupPayloads=groupRepository.getGroupsEmployesId(employesId);
            if (groupPayloads != null){
                return ResponseEntity.ok(new Result(true,"get getGroupsemployesId",groupPayloads));
            }
            return new ResponseEntity(new Result(false,"getGroupsemployesId employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error groups",e.getMessage());
            return new ResponseEntity(new Result(false,"getGroupsemployesId employe error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    public ResponseEntity<?> editGroups(GroupPayload payload, String hashId){
        try {
            Groups groups=groupRepository.findById(payload.getId()).orElseThrow(()->new ResourceNotFoundException("group not found"));
            groups.setName(payload.getName());
            groups.setPrice(payload.getPrice());
            groups.setTitle(payload.getTitle());
            groups.setDiscription(payload.getDiscription());
            groups.setDuration(payload.getDuration());
            groups.setCapacity(payload.getCapacity());
            groups.setImg(Arrays.asList(attachmentRepository.findByHashId1(hashId).orElseThrow(()->new ResourceNotFoundException("Attachment not found"))));
            groups.setEmployees(Arrays.asList(employesRepository.findById(payload.getEmployeesId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found"))));
            groups.setSubject(Arrays.asList(subjectRepository.findById(payload.getSubjectId()).orElseThrow(()->new ResourceNotFoundException("CenterBranches not found"))));
            groups=groupRepository.save(groups);
            if (groups != null){
                return ResponseEntity.ok(new Result(true,"edit group",groups));
            }
            return new ResponseEntity(new Result(false,"edit group error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error group",e.getMessage());
            return new ResponseEntity(new Result(false,"edit group error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteGroup(Long groupId){
        try {
            groupRepository.deleteById(groupId);
            return ResponseEntity.ok("delete succesfull");
        }catch (Exception e){
            log.error("error group",e.getMessage());
            return new ResponseEntity(new Result(false,"delete group error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteGroupSubjectId(Long subjectId){
        try {
            if (groupRepository.deleteGroupsSubjectId(subjectId)) {
                return ResponseEntity.ok("delete succesfull");
            }
            return new ResponseEntity(new Result(false,"delete group error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error group",e.getMessage());
            return new ResponseEntity(new Result(false,"delete group error",null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> deleteGroupEmployeId(Long employeId) {
        try {
            if (groupRepository.deleteGroupsEmployeeId(employeId)) {
                return ResponseEntity.ok("delete succesfull");
            }
            return new ResponseEntity(new Result(false, "delete group error", null), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("error group", e.getMessage());
            return new ResponseEntity(new Result(false, "delete group error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
