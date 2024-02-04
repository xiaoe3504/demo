package com.psy.demo.controller;

import com.psy.demo.utils.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class MusicController {
    private static String path = "src/main/resources/static/music";

    @GetMapping("mp3/{id}")
    public ResponseEntity<Resource> downloadMp3(@PathVariable("id") Integer id) throws Exception {
        File file = FileUtils.getFile(path, id);

        HttpHeaders header = new HttpHeaders();
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=zaijianmonika.mp3");
        header.setContentType(MediaType.valueOf("audio/mp3"));
        return ResponseEntity.ok()
                .headers(header)
                .body(new FileSystemResource(file));
    }
}
