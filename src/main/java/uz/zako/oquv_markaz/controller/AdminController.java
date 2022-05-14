package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.*;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.JwtTokenProvider;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/admin", produces= MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final CategoryService categoryService;
    private final NewsService newsService;
    private final AttachmentService attachmentService;
    private final CourcesService courcesService;
    private final TeacherService teacherService;
    private final GroupsService groupsService;
    private final UserRepository userRepository;

    @PostMapping("/v1/category")
    public ResponseEntity<?> addCategory(@RequestBody CategoryPayload payload) {
        return categoryService.addCategory(payload);
    }

    @PostMapping("/v1/news")
    public ResponseEntity<?> addNews(@RequestBody NewsPayload payload) {
        System.out.println("news save=" + payload);
        return newsService.addNews(payload);
    }

    @PostMapping("/v1/courses")
    public ResponseEntity<?> addCources(@RequestBody CourcesPayload payload) {
        return courcesService.addCources(payload);
    }

    @PostMapping("/v1/teacher")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherPayload payload) {
        System.out.println(payload.toString());
        return teacherService.addTeacher(payload);
    }

    @PostMapping("/v1/groups")
    public ResponseEntity<?> addGroups(@RequestBody GroupPayload payload) {
        System.out.println(payload.toString());
        return groupsService.addGroups(payload);
    }

    @PostMapping("/v1/{userId}")
    public boolean addUserIdGroup(@RequestBody GroupPayload payload, @PathVariable("userId") Long id) {
        return userService.saveUserIdGroup(payload,id);
    }


    @PostMapping("/v1/user")
    public ResponseEntity<?> addUser(@RequestBody UserPayload payload) {

        System.out.println(payload.toString());
        return ResponseEntity.ok(userService.save(payload));

    }

    @PutMapping("/v1/admin")
    public ResponseEntity<?> addAdmin(@RequestBody UserPayload payload) {
        System.out.println("admin ga keldi="+payload);
        return ResponseEntity.ok(userService.saveAdmin(payload));

    }

    @PutMapping("/v1/category")
    public ResponseEntity<?> editCategory(@RequestBody CategoryPayload payload) {
        return categoryService.editCategory(payload);
    }

    @PutMapping("/v1/news")
    public ResponseEntity<?> editNews(@RequestBody NewsPayload payload) {
        System.out.println("edit save=" + payload);
        return newsService.editNews(payload);
    }

    @PutMapping("/v1/courses")
    public ResponseEntity<?> editCources(@RequestBody CourcesPayload payload) {
        return courcesService.editCources(payload);
    }

    @PutMapping("/v1/teacher")
    public ResponseEntity<?> editTeacher(@RequestBody TeacherPayload payload) {
        System.out.println(payload.toString());
        return teacherService.editTeacher(payload);
    }

    @PutMapping("/v1/groups")
    public ResponseEntity<?> editGroups(@RequestBody GroupPayload payload) {
        System.out.println(payload.toString());
        return groupsService.editGroups(payload);
    }

    @PutMapping("/v1/user")
    public ResponseEntity<?> editUser(@RequestBody UserPayload payload) {

        System.out.println(payload.toString());
        return ResponseEntity.ok(userService.editUser(payload));

    }

    @DeleteMapping("/category/{categoryId}")
    public boolean deleteCategory(@PathVariable("categoryId") Long id) {

        System.out.println("delete category="+id);
        return categoryService.deleteById(id);
    }

    @DeleteMapping("/news/{newsId}")
    public boolean deleteNewsId(@PathVariable("newsId") Long id) {
        return newsService.deleteById(id);
    }

    @DeleteMapping("/cource/{courseId}")
    public boolean deleteCources(@PathVariable("courseId") Long id) {
        return courcesService.deleteById(id);
    }

    @DeleteMapping("/teacher/{teacherId}")
    public boolean deleteTeacher(@PathVariable("teacherId") Long id) {
        System.out.println(id + " delete teacherga keldi");
        return teacherService.deleteById(id);
    }

    @DeleteMapping("/user/{userId}")
    public boolean deleteUser(@PathVariable("userId") Long id) {

        return userService.deleteById(id);

    }


    @GetMapping("/v1/categorys")
    public ResponseEntity<?> getAllCategorys() {
        return categoryService.getAllCategorys();
    }

    @GetMapping("/v1/Cource")
    public ResponseEntity<?> getAllCource() {
        return courcesService.getAllCources();
    }

    @GetMapping("/v1/Groups")
    public ResponseEntity<?> getAllGroups() {
        return groupsService.getAllGroups();
    }

    @GetMapping("/v1/users/{groupId}")
    public ResponseEntity<?> getGroupIdUsers(@PathVariable("groupId") Long id) {
        System.out.println("id="+id);
        return userService.getGroupsIdUsers(id);
    }

    @GetMapping("/v1/News")
    public ResponseEntity<?> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/v1/Teacher")
    public ResponseEntity<?> getAllTeacher() {
        System.out.println("getMappin teacherga keldi:)");
        return teacherService.getAllTeachers();
    }

    @GetMapping("/v1/{teacherId}")
    public ResponseEntity<?> getTeacherIdGroups(@PathVariable("teacherId") Long id) {
        return teacherService.getTeacherIdGroups(id);
    }

    @GetMapping("/v1/user")
    public ResponseEntity<?> getAllUser() {

        return userService.getAllUser();

    }


}
