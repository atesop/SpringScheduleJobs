package com.atesop.spring.job.ScheduledDemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * User: atesop
 * Date: 16-4-24
 * TIME: 下午4:22
 * 简单任务：能够在任务周期内单线程处理完成。
 */
@Component
public class SimpleScheduledJob {

    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    @Autowired
    private ScheduledComponent scheduledComponent;
    @Scheduled(cron = "0 0 1 * * *")
    public void cronTask() {
        int sleepSec = 60;
        SimpleScheduledWorker simpleScheduledWorker = new SimpleScheduledWorker(scheduledComponent, sleepSec);
        executor.schedule(simpleScheduledWorker, 1000, TimeUnit.MILLISECONDS);
    }
}

