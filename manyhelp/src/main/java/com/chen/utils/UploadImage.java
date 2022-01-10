package com.chen.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class UploadImage {
    @Autowired
    private GetTokenUrl getTokenUrl;
    @Autowired
    private FastFileStorageClient storageClient;
    public String upload(MultipartFile file) {
        List<String> CONTENT_TYPES = Arrays.asList("image/jpeg", "image/gif","image/png");
        //    获取文件名称
        String originalFilename=file.getOriginalFilename();
//        校验文件的类型:image/png
        String contentType=file.getContentType();
        if (!CONTENT_TYPES.contains(contentType)){
            log.info("文件类型不合法:{}",originalFilename);
            return null;
        }
        try{
//            校验文件内容
            BufferedImage bufferedImage= ImageIO.read(file.getInputStream());
            if (bufferedImage==null){
                log.info("文件内容不和发：{}",originalFilename);
                return null;
            }
//            保存到服务器
//            System.out.println(originalFilename);
            String ext = StringUtils.substringAfterLast(originalFilename, ".");
//            System.out.println(ext);  :这是获取图片后缀png
            StorePath storePath=storageClient.uploadFile(file.getInputStream(),file.getSize(),ext,null);
//            生成地址返回
            log.info("上传图片成功");
            String avatarUrl=getTokenUrl.getTokenUrl(storePath.getFullPath());
            return avatarUrl;
        } catch (IOException e) {
            log.info("服务器内部错误");
            e.printStackTrace();
            return null;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
