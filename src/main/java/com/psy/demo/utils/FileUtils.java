package com.psy.demo.utils;

import java.io.File;

public class FileUtils {

    public static File getFile(String path, int id) throws Exception {
        // 检查参数是否为目录
        File folder = new File(path);
        // 获取某个id的file
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            throw new Exception("null file");
        }
        if (id >= files.length || id < 0) {
            return files[0];
        }
        return files[id];
    }

}
