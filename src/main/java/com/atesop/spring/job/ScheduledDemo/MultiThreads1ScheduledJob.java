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
 * 多线程任务：能够在任务周期内多线程处理完成。
 * 如果任务在任务周期内未完成，则会导致线程游离，甚至整个任务挂掉。
 */
@Component
public class MultiThreads1ScheduledJob {

    private static ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
    @Autowired
    private ScheduledComponent scheduledComponent;
    @Scheduled(cron = "0 0 1 * * *")
    public void cronTask() {
        int subWorkerNum = 100;
        MultiThreadsScheduledWorker multiThreadsScheduledWorker
                = new MultiThreadsScheduledWorker(scheduledComponent, subWorkerNum);
        executor.schedule(multiThreadsScheduledWorker, 1000, TimeUnit.MILLISECONDS);
    }
}

