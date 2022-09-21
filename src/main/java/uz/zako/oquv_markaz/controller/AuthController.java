package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.*;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.AuthService;
import uz.zako.oquv_markaz.security.JwtTokenProvider;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final NewsService newsService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final SecurityUtils securityUtils;
    private final AuthService authService;


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

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody AuthTokenPayload payload) {
        try {
            return ResponseEntity.ok(authService.refreshToken(payload));
        } catch (Exception e) {
            log.error("error in refresh token - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error(e.getMessage()));
        }
    }

    @PostMapping("/save")
    public boolean saveUser(@RequestBody UserPayload payload) {
        return userService.save(payload);
    }


//    @GetMapping("/category/{id}")
//    public ResponseEntity<?> getIdCategory(@PathVariable("id") Long id) {
//
//        return categoryService.getIdCategory(id);
//
//    }
//
//    @GetMapping("/news/{categoryId}")
//    public ResponseEntity<?> getCategoryIdNews(@PathVariable("categoryId") Long id) {
//        System.out.println("keldi /news/categoryId");
//        return newsService.getCategoryIdNews(id);
//
//    }
//
//
//    @GetMapping("/page/news")
//    public Page<NewsPayload> getPageNews(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "8") int size) {
//
//        return newsService.getPageNews(page, size);
//
//    }
//
//    @GetMapping("/news/view/{id}")
//    public ResponseEntity<?> getIdNews(@PathVariable("id") Long id) {
//        return newsService.getNewsBody(id);
//    }

    @GetMapping("/me")
    public ResponseEntity<?> getMe() {
        String username = securityUtils.getCurrentUser().orElseThrow(() -> new RuntimeException("error"));
        System.out.println(username + "=user");
        return ResponseEntity.ok(userRepository.findByUsername(username));
    }

}
