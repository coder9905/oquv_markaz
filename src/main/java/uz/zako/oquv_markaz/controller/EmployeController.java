package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.UserPayload;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.JwtTokenProvider;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.EmployeeService;
import uz.zako.oquv_markaz.service.UserService;

@RestController
@RequestMapping("/api/employe/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeController {

    private final EmployeeService employeeService;

    @GetMapping("/getone/employe/{emploeId}")
    public ResponseEntity<?> getOneEmploye(@PathVariable("employeId") Long id) {
        return employeeService.getOne(id);
    }



}
