package com.tz.tpcs.util;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件工具类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/28 17:14
 */
public final class FileUtil {

    /** private constructor */
    private FileUtil() {
    }

    /**
     * 将文件拷贝到指定路径(UUID作为文件名+原后缀名)
     * @param file 文件
     * @param realPath 服务器端的文件夹路径
     * @return 保存后的文件名
     */
    public static String copyFileToPath(MultipartFile file, String realPath) {
        //原文件名
        String originalFilename = file.getOriginalFilename();
        //截取后缀名
        int pos = originalFilename.lastIndexOf(".");
        String suffix = originalFilename.substring(pos);
        //产生随机文件名(UUID+后缀名)
        String uuid = UUID.randomUUID().toString();
        String savedFileName = uuid + suffix;
        String fullPath = realPath + File.separator + savedFileName;
        //保存文件
        try {
            FileUtils.copyInputStreamToFile(file.getInputStream(), new File(fullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return savedFileName;
    }
}
