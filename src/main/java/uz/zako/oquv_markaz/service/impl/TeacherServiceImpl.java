package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Cource;
import uz.zako.oquv_markaz.entity.Teacher;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.CourcesPayload;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.payload.TeacherPayload;
import uz.zako.oquv_markaz.repository.AttachmentRepository;
import uz.zako.oquv_markaz.repository.CourcesRepository;
import uz.zako.oquv_markaz.repository.GroupsRepository;
import uz.zako.oquv_markaz.repository.TeachersRepository;
import uz.zako.oquv_markaz.service.CourcesService;
import uz.zako.oquv_markaz.service.TeacherService;

import java.util.Arrays;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeachersRepository teachersRepository;
    private final AttachmentRepository attachmentRepository;
    private final GroupsRepository groupsRepository;

    @Override
    public ResponseEntity<?> addTeacher(TeacherPayload teacherPayload) {
        try {
            Teacher teacher = new Teacher();
            teacher.setFullName(teacherPayload.getFullName());
            teacher.setPhone(teacherPayload.getPhone());
            teacher.setImg(attachmentRepository.findByHashId(teacherPayload.getImg()));
            teacher.setGroups(Arrays.asList(groupsRepository.findByIdGroup(teacherPayload.getGroupsName())));
            return ResponseEntity.ok(teachersRepository.save(teacher));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> editTeacher(TeacherPayload teacherPayload) {
        try {
            Teacher teacher = new Teacher();
            teacher.setId(teacherPayload.getId());
            teacher.setFullName(teacherPayload.getFullName());
            teacher.setPhone(teacherPayload.getPhone());
            teacher.setImg(attachmentRepository.findByHashId(teacherPayload.getImg()));
            teacher.setGroups(Arrays.asList(groupsRepository.findByIdGroup(teacherPayload.getGroupsName())));
            return ResponseEntity.ok(teachersRepository.save(teacher));
        } catch (Exception e) {
            log.error("add news error -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public Page<TeacherPayload> getPageTeacher(int page, int size) {

        PageRequest request = PageRequest.of(page, size);
        Page<TeacherPayload> teachers = teachersRepository.findAllByPage(request);
        for (int i = 0; i < teachers.getContent().size(); i++) {
            teachers.getContent().get(i).setImg(teachers.getContent().get(i).getImg());
        }
        return teachers;

    }

    @Override
    public ResponseEntity<?> getAllTeachers() {
        try {
            return ResponseEntity.ok(teachersRepository.findAll());
        } catch (Exception e) {
            log.error("error getAllTeachers -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getTeacherIdGroups(Long id) {
        try {
            return ResponseEntity.ok(groupsRepository.getByTeacherIdGroup(id));
        } catch (Exception e) {
            log.error("error getAllTeachers -> {}", e.getMessage());
            return new ResponseEntity(new Result(false, "error", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            groupsRepository.getByTeacherIdGroup(id);
            return true;
        } catch (Exception e) {
            log.error("error getAllTeachers -> {}", e.getMessage());
            return false;
        }
    }

}
