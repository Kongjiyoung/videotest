package org.example.video;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class HlsController {

    private static final String UPLOAD_DIR = "uploads/";

    @GetMapping("/upload-form")
    public String uploadForm() {
        return "hls-video";
    }

    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("title") String title,
                              @RequestParam("video") MultipartFile video,
                              Model model) throws IOException {
        if (video.isEmpty()) {
            model.addAttribute("message", "Please select a video file to upload.");
            return "hls-video";
        }

        // 디렉토리 생성
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 파일 저장
        String videoFileName = UUID.randomUUID().toString() + "_" + video.getOriginalFilename();
        Path filePath = uploadPath.resolve(videoFileName);
        Files.copy(video.getInputStream(), filePath);

        // 업로드 성공 메시지
        model.addAttribute("message", "File uploaded successfully: " + videoFileName);
        return "hls-video";
    }
}