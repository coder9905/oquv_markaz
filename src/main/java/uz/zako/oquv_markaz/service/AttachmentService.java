package uz.zako.oquv_markaz.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import uz.zako.oquv_markaz.entity.Attachment;

public interface AttachmentService {
    ResponseEntity<?> upload(MultipartFile multipartFile);

    ResponseEntity<?> preview(String hashId);

    ResponseEntity<?> download(String hashId);

    ResponseEntity<?> delete(String hashId);

    ResponseEntity<?> getAllAttachment();
}
