package com.atesop.spring.job.ScheduledDemo;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * User: atesop
 * Date: 16-4-24
 * TIME: 下午4:22
 * 队列任务:定时提交任务到队列，使用 PostConstruct 注解
 */
@Component
public class BlockingQueueScheduledJob {

    private static ExecutorService workerExecutorService = Executors.newFixedThreadPool(4);

    @Autowired
    private ScheduledComponent scheduledComponent;

    private BlockingQueue<Integer> integerBlockingQueue;

    @PostConstruct
    void init() {
        integerBlockingQueue = new LinkedBlockingQueue<Integer>();
        BlockingQueueScheduledWorker blockingQueueScheduledWorker = new BlockingQueueScheduledWorker(
                scheduledComponent, integerBlockingQueue
        );
        for (int i = 0; i != 4; ++i) {
            workerExecutorService.execute(blockingQueueScheduledWorker);
        }
    }

    @Scheduled(cron = "0 0 1 * * *")
    public void cronTask() {
        int subWorkerNum = 100;
        for (int i = 0; i != subWorkerNum; ++i) {
            int randomSleepSec = RandomUtils.nextInt(0,100);
            integerBlockingQueue.add(randomSleepSec);
        }
    }
}

