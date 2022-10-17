package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Employee;
import uz.zako.oquv_markaz.entity.Nb;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.EmployePayload;
import uz.zako.oquv_markaz.payload.NbPayload;
import uz.zako.oquv_markaz.repository.*;
import uz.zako.oquv_markaz.service.EmployeeService;
import uz.zako.oquv_markaz.service.NbService;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NbServiceImpl implements NbService {

    private final NbRepository nbRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<?>  save(NbPayload payload){
        try {
            Nb nb=new Nb();
            nb.setUser(Arrays.asList(userRepository.findByIdUser(payload.getUserId())));
            nb.setUsers(Arrays.asList(userRepository.findByIdUser(payload.getAdminId())));
            nb=nbRepository.save(nb);
            if (nb != null) {
                return ResponseEntity.ok("save succesfull");
            }
            return new ResponseEntity(new Result(false,"error save nb",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error nb",e.getMessage());
            return new ResponseEntity(new Result(false,"nb saqlashda hatolik",null), HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> getOne(Long userId){
        try {
            List<NbPayload> nb=nbRepository.getUserIdNb(userId);
            if (nb!=null) {
                return ResponseEntity.ok(new Result(true, "getOneEmploye", nb));
            }
            return new ResponseEntity(new Result(false,"get One employe error",null), HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error employes",e.getMessage());
            return new ResponseEntity(new Result(false,"get One employe error",null), HttpStatus.CONFLICT);
        }
    }


}
