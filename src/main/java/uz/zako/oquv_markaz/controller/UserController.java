package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.JwtTokenProvider;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityUtils securityUtils;

    @PostMapping("/save")
    public boolean saveUser(@RequestBody UserPayload payload) {
        return userService.save(payload);
    }

    @PostMapping("/token")
    public ResponseEntity<?> login(@RequestBody UserPayload payload) {

        if (true) {
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
        }
        return ResponseEntity.ok(new Result(false, "error", null));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
        System.out.println(username);
        return ResponseEntity.ok(userRepository.findByUsername(username));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editUser(@RequestBody UserPayload payload) {
        String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
        System.out.println(username + "=edit username");
        return ResponseEntity.ok(userService.save(payload));
    }

}
