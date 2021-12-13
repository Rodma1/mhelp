package com.chen.utils;
//编写一个生成token防盗链的工具类

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import lombok.extern.slf4j.Slf4j;
import org.csource.fastdfs.ProtoCommon;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class GetTokenUrl {
    @Value("${fdfs.web-server-url}")
    private String fastDfsUrl;
    @Value("${fdfs.http.http_secret_key}")
    private String fastDfsKey;
    /**
     * 获取带有token的访问地址
     *
     * @param fileUrl 示例：group1/M00/00/00/L2ZUml6QisqAUJE3AIOPO1HT6Bo274.mp4
     * @return java.lang.String 示例：http://yourIp:port/files/group1/M00/00/00/L2ZUml6QisqAUJE3AIOPO1HT6Bo274.mp4?token=e9a6ae7f1ecca6fed51e248c6a10d3bc&ts=1589361883
     */
    public String getTokenUrl(String fileUrl) throws Exception {
        String path = StorePath.parseFromUrl(fileUrl).getPath();
        //时间戳 单位为秒
        int ts = (int) (System.currentTimeMillis() / 1000);
        String token;
        try {
            token = ProtoCommon.getToken(path, ts, fastDfsKey);
        } catch (Exception e) {
            log.error("获取token异常", e);
            throw new Exception("FastDFS获取token异常");
        }

        return "http://"+fastDfsUrl + "/"+fileUrl + "?token=" + token + "&ts=" + ts;
    }
}
