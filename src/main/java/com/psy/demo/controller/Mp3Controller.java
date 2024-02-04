package com.psy.demo.controller;

import com.psy.demo.global.BaseException;
import com.psy.demo.global.NotGlobalControllerAdvice;
import com.psy.demo.utils.FileUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController

public class Mp3Controller {
    private static String path = "src/main/resources/static/music";

    @GetMapping("mp3/{id}")
    @NotGlobalControllerAdvice
    public void downloadMp3(@PathVariable("id") Integer id, HttpServletResponse response) {
        File file;
        try {
            file = FileUtils.getFile(path, id);
        } catch (Exception e) {
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
