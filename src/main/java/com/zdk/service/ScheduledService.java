package com.zdk.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author zdk
 * @date 2021/5/30 14:59
 */
@Service
public class ScheduledService {
    //在一个特定的时间执行这个方法
    //秒 分 时 日 月 周几

    @Scheduled(cron = "0 1 15 * * ?")
    public void hello(){
        System.out.println("执行hello方法");
    }
}
