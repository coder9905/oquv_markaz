package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.Moon;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.MoonPayload;
import uz.zako.oquv_markaz.payload.MoonPayload;
import uz.zako.oquv_markaz.repository.MoonRepository;
import uz.zako.oquv_markaz.service.MoonService;
import uz.zako.oquv_markaz.service.MoonService;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MoonServiceImpl implements MoonService {

    private final MoonRepository moonRepository;

    @Override
    public ResponseEntity<?> saveMoon(MoonPayload payload){
        try {
            Moon moon=new Moon();
            moon.setName(payload.getName());
            moon=moonRepository.save(moon);
            if (moon != null){
                return ResponseEntity.ok(new Result(true,"save succesfull",moon));
            }
            return new ResponseEntity(new Result(false,"save error moon",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error moon",e.getMessage());
            return new ResponseEntity(new Result(false,"save error moon",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> editMoon(Long moonId, MoonPayload payload){
        try {
            Moon moon=moonRepository.findById(moonId).orElseThrow(()->new ResourceNotFoundException("moon not found"));
            moon.setName(payload.getName());
            moon=moonRepository.save(moon);
            if (moon != null){
                return ResponseEntity.ok(new Result(true,"edit succesfull",moon));
            }
            return new ResponseEntity(new Result(false,"edit error moon",null),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            log.error("error moon",e.getMessage());
            return new ResponseEntity(new Result(false,"edit error moon",null),HttpStatus.CONFLICT);
        }
    }

    @Override
    public ResponseEntity<?> getAllMoon(){
        try {
            List<Moon> moons=moonRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
            return ResponseEntity.ok(moons);
        }catch (Exception e){
            log.error("error moon",e.getMessage());
            return new ResponseEntity(new Result(false,"edit error moon",null),HttpStatus.CONFLICT);
        }
    }


}
