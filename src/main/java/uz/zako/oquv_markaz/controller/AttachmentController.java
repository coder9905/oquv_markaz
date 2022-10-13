package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.zako.oquv_markaz.service.AttachmentService;

@RestController
@RequestMapping("/api/file")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AttachmentController {

    private  final AttachmentService attachmentService;

    @PostMapping("/")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) {
        System.out.println(multipartFile.toString());
        return attachmentService.upload(multipartFile);
    }

    @GetMapping("/preview/{hashId}")
    public ResponseEntity<?> getPreview(@PathVariable("hashId") String hashId) {
        return attachmentService.preview(hashId);
    }

    @GetMapping("/download/{hashId}")
    public ResponseEntity<?> getDownload(@PathVariable("hashId") String hashId) {
        return attachmentService.download(hashId);
    }

    @DeleteMapping("/delete/{hashId}")
    public ResponseEntity<?> getDelete(@PathVariable("hashId") String hashId) {
        return attachmentService.delete(hashId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAttachment() {
        return attachmentService.getAllAttachment();
    }

}
