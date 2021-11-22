package com.chen.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取页码
 */
@Component
public class PageUtils {
    @Autowired(required = false)
    private HttpServletRequest req;
    public Page getPage(){
//        几页
        int current= ServletRequestUtils.getIntParameter(req,"cuurent",1);
//        一页里面的数量
        int size=ServletRequestUtils.getIntParameter(req,"size",10);
//        返回页数
        return new Page(current,size);
    }
}
