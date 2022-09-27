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
        try {
            return ResponseEntity.ok(authService.createToken(payload));
        } catch (Exception e) {
            log.error("error in login - {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Result.error(e.getMessage()));
        }
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

}
