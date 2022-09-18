package uz.zako.oquv_markaz.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileUrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.zako.oquv_markaz.model.Result;
import uz.zako.oquv_markaz.service.AttachmentService;

import java.net.MalformedURLException;
import java.net.URLEncoder;


@RestController
@RequestMapping("/api/file")
public class AttachmentController {

    @Autowired(required = false)
    private AttachmentService attachmentService;

    @GetMapping("/")
    public String get() {
        return "Hello:)";
    }

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

    @GetMapping("/delete/{hashId}")
    public ResponseEntity<?> getDelete(@PathVariable("hashId") String hashId) {
        return attachmentService.delete(hashId);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAttachment() {
        return attachmentService.getAllAttachment();
    }

}
