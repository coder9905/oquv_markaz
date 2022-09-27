package uz.zako.oquv_markaz.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import uz.zako.oquv_markaz.entity.CenterBranches;
import uz.zako.oquv_markaz.entity.Phone;
import uz.zako.oquv_markaz.entity.TrainingCenter;
import uz.zako.oquv_markaz.entity.User;
import uz.zako.oquv_markaz.exception.ResourceBadRequestException;
import uz.zako.oquv_markaz.exception.ResourceNotFoundException;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.payload.PhonePayload;
import uz.zako.oquv_markaz.payload.TrainingCenterPayload;
import uz.zako.oquv_markaz.repository.CenterBranchesRepository;
import uz.zako.oquv_markaz.repository.PhoneRepository;
import uz.zako.oquv_markaz.repository.TrainingCenterRepository;
import uz.zako.oquv_markaz.repository.UserRepository;
import uz.zako.oquv_markaz.security.SecurityUtils;
import uz.zako.oquv_markaz.service.PhoneService;
import uz.zako.oquv_markaz.service.TrainingCenterService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {

    private final TrainingCenterRepository trainingCenterRepository;
    private final CenterBranchesRepository centerBranchesRepository;
    private final SecurityUtils securityUtils;
    private final PhoneRepository phoneRepository;
    private final UserRepository userRepository;


    @Override
    public List<PhonePayload> savePhone(List<PhonePayload> payloads){
        try {
            if (payloads!=null) {
                return payloads.stream().peek(phonePayload -> {
                    Phone phone = new Phone();
                    phone.setPhone(phonePayload.getPhone());
                    phoneRepository.save(phone);
                }).collect(Collectors.toList());
            }
            throw new ResourceBadRequestException("send properly body");
        }catch (Exception e){
            log.error("Error save phone",e.getMessage());
            return null;
        }
    }
}
