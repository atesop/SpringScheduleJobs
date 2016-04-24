package com.atesop.spring.job.ScheduledDemo;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * User: atesop
 * Date: 16-4-24
 * TIME: 下午4:22
 * 多线程任务：能够在任务周期内多线程处理完成。
 * 如果任务在任务周期内未完成，则会导致线程游离，甚至整个任务挂掉。
 */
@Component
public class MultiThreads2ScheduledJob {

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    @Autowired
    private ScheduledComponent scheduledComponent;
    @Scheduled(cron = "0 0 1 * * *")
    public void cronTask() {
        int subWorkerNum = 100;
//        if (executorService.isShutdown()) {
//            executorService = Executors.newFixedThreadPool(4);
//        }
        for (int i = 0; i != subWorkerNum; ++i) {
            int randomSleepSec = RandomUtils.nextInt(0, 100);
            executorService.execute(new SimpleScheduledWorker(scheduledComponent, randomSleepSec));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
        }
    }
}

