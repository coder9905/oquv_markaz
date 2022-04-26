package uz.zako.oquv_markaz.service;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import uz.zako.oquv_markaz.payload.CourcesPayload;
import uz.zako.oquv_markaz.payload.NewsPayload;
import uz.zako.oquv_markaz.payload.TeacherPayload;

public interface TeacherService {

    ResponseEntity<?> addTeacher(TeacherPayload teacherPayload);

    Page<TeacherPayload> getPageTeacher(int page, int size);

    ResponseEntity<?> getAllTeachers();

    ResponseEntity<?> getTeacherIdGroups(Long id);

    ResponseEntity<?> editTeacher(TeacherPayload payload);

    boolean deleteById(Long id);
}
