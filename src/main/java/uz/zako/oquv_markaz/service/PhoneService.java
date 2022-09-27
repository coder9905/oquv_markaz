package uz.zako.oquv_markaz.service;

import uz.zako.oquv_markaz.payload.PhonePayload;

import java.util.List;

public interface PhoneService {
    List<PhonePayload> savePhone(List<PhonePayload> payloads);
}
