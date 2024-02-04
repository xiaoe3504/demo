package com.psy.demo.controller;

import com.psy.demo.global.BaseException;
import com.psy.demo.global.NotGlobalControllerAdvice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 直接用云文件不用这个了
 */
@RestController
@Deprecated()
public class Mp3Controller {

    @Value("classpath*:/static/music/*")
    private Resource[] mp3Resources;

    public List<Resource> getAllResources() {
        return Stream.of(mp3Resources).collect(Collectors.toList());
    }

    @GetMapping("mp3/{id}")
    @NotGlobalControllerAdvice
    public void downloadMp3(@PathVariable("id") Integer id, HttpServletResponse response) {
        File file;
        Resource resource = getAllResources().get(id);
        try {
            file = new File(resource.getURL().getFile());
        } catch (IOException e) {
            throw new BaseException(e);
        }
        response.setContentType("audio/mp3");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        String path = file.getAbsolutePath();
        try (OutputStream outputStream = response.getOutputStream();
             FileInputStream fileInputStream = new FileInputStream(path)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new BaseException(e);
        }

    }
}
