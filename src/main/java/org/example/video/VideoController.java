package org.example.video;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Controller
public class VideoController {

    @GetMapping("/api/videoUrl")
    @ResponseBody
    public ResponseEntity<Map<String, String>> getVideoUrl() {
        // 비디오 URL을 담기 위한 Map 객체 생성
        Map<String, String> videoUrl = new HashMap<>();
        videoUrl.put("url", "/sample.mp4"); // 비디오 파일의 경로를 "/sample.mp4"로 설정

        // ResponseEntity 객체를 생성하여 HTTP 응답을 처리
        return ResponseEntity.ok(videoUrl); // Map 객체를 ResponseEntity에 담아서 반환
    }
}