package com.example.hotfixlibrary.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * author : Administrator
 * date : 2019/4/21 0021 18:43
 * description :
 */
public class FileUtils {


    /**
     * 将路径复制到目标文件下
     */
    public static boolean copyFile(File src, File dest) {
        boolean iret = false;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {
            if (!dest.exists()) {
                dest.createNewFile();
            }
            inChannel = new FileInputStream(src).getChannel();
            outChannel = new FileOutputStream(dest).getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            iret = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inChannel != null) {
                    inChannel.close();
                }
                if (outChannel != null) {
                    outChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return iret;
    }

    /**
     * 将路径复制到目标文件夹下
     */
    public static boolean copyDirectory(File src, File dest) {
        if (!src.isDirectory()) {
            return false;
        }
        if (!dest.isDirectory() && !dest.mkdirs()) {
            return false;
        }
        File[] files = src.listFiles();
        for (File file : files) {
            File destFile = new File(dest, file.getName());
            if (file.isFile()) {
                return copyFile(file, destFile);
            } else if (file.isDirectory()) {
                return copyDirectory(file, destFile) ;
            }
        }
        return true;
    }

}
