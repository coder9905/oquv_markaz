package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.JwtTokenProvider;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.UserService;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityUtils securityUtils;

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
        System.out.println(userRepository.findByUsername(username));
        return ResponseEntity.ok(userRepository.findByUsername(username));
    }

    @PostMapping("/save/user")
    public ResponseEntity<?> saveUser(@RequestBody UserPayload payload) {
        return userService.saveUser(payload);
    }

    @PutMapping("/edit/user")
    public ResponseEntity<?> editUser(@RequestBody UserPayload payload) {
        return userService.editUser(payload);
    }

    @GetMapping("/all/user")
    public ResponseEntity<?> getAllUser(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/delete/user/{userId}")
    public ResponseEntity<?> deleteUserId(@PathVariable("userId") Long id) {
        return userService.deleteUserId(id);
    }

    @DeleteMapping("/deleteGroupId/user/{groupId}")
    public ResponseEntity<?> deleteUserGroupId(@PathVariable("groupId") Long id) {
        return userService.deleteUserGroupId(id);
    }

}
