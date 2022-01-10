package com.chen.common.test;

import java.util.Date;

public class textcurrentTimeMillis {
    public static void main(String[] args) {
        Date timesdata=new Date();
//        data其实调用的是currentTimeMillis
        System.out.println(timesdata.toString());
//        获取毫秒数
        long timestamp=System.currentTimeMillis();
        System.out.println(timestamp);

    }
}
