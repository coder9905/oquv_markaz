package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/admin")
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
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityUtils securityUtils;

    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody UserPayload payload) {

        System.out.println("Keldiku admin token");

        try {
            User user = userRepository.findByUsername(payload.getUsername());
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getUsername(), payload.getPassword()));
            String token = jwtTokenProvider.createToken(user.getUsername(), user.getRoles());
            System.out.println(token.toString());
            if (token == null) {
                return new ResponseEntity(new Result(false, "error", null), HttpStatus.BAD_REQUEST);
            }
            Map<String, Object> map = new HashMap();
            map.put("token", token);
            map.put("username", true);
            return ResponseEntity.ok(map);
        } catch (Exception e) {
            return ResponseEntity.ok(new Result(false, "error", null));
        }
    }

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
    public boolean addUserIdGroup(@RequestBody GroupPayload payload,@RequestParam("id") Long id) {

        return userService.saveUserIdGroup(payload,id);

    }


    @PostMapping("/v1/user")
    public ResponseEntity<?> addUser(@RequestBody UserPayload payload) {

        System.out.println(payload.toString());
        return ResponseEntity.ok(userService.save(payload));

    }

    @PutMapping("/v1/admin/{id}")
    public ResponseEntity<?> addAdmin(@RequestParam("id") Long id, @RequestBody UserPayload payload) {
        System.out.println("admin ga keldi="+id);
        return ResponseEntity.ok(userService.saveAdmin(id));

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

//    @PutMapping("/v1/admin/{id}")
//    public ResponseEntity<?> editAdmin(@RequestParam("id") Long id) {
//
//        return ResponseEntity.ok(userService.editAdmin(id));
//
//    }

    @DeleteMapping("/v1/{categoryId}")
    public boolean deleteCategory(@RequestParam("categoryId") Long id) {
        return categoryService.deleteById(id);
    }

    @DeleteMapping("/v1/{newsId}")
    public boolean deleteNewsId(@RequestParam("newsId") Long id) {
        return newsService.deleteById(id);
    }

    @DeleteMapping("/v1/{courseId}")
    public boolean deleteCources(@RequestParam("courseId") Long id) {
        return courcesService.deleteById(id);
    }

    @DeleteMapping("/v1/{teacherId}")
    public boolean deleteTeacher(@RequestParam("teacherId") Long id) {
        return teacherService.deleteById(id);
    }

    @DeleteMapping("/v1/{userId}")
    public boolean deleteUser(@RequestParam("userId") Long id) {

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
    public ResponseEntity<?> getGroupIdUsers(@RequestParam("groupId") Long id) {
        return userService.getGroupsIdUsers(id);
    }

    @GetMapping("/v1/News")
    public ResponseEntity<?> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/v1/Teacher")
    public ResponseEntity<?> getAllTeacher() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/v1/{teacherId}")
    public ResponseEntity<?> getTeacherIdGroups(@RequestParam("teacherId") Long id) {
        return teacherService.getTeacherIdGroups(id);
    }

    @GetMapping("/v1/user")
    public ResponseEntity<?> getAllUser() {

        return userService.getAllUser();

    }


}
