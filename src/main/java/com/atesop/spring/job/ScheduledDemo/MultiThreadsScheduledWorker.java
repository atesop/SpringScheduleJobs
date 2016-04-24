package com.atesop.spring.job.ScheduledDemo;

import org.apache.commons.lang.math.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: atesop
 * Date: 16-4-24
 * TIME: 下午4:22
 */
public class MultiThreadsScheduledWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(MultiThreadsScheduledWorker.class);
    private static ExecutorService executorService = Executors.newFixedThreadPool(4);
    private ScheduledComponent scheduledComponent;
    private int subWorkerNum;

    public MultiThreadsScheduledWorker(ScheduledComponent scheduledComponent, int subWorkerNum) {
        this.scheduledComponent = scheduledComponent;
        this.subWorkerNum = subWorkerNum;
    }

    public void run() {
        if (executorService.isShutdown()) {
            executorService = Executors.newFixedThreadPool(4);
        }
        for (int i = 0; i != subWorkerNum; ++i) {
            int randomSleepSec = RandomUtils.nextInt()/100;
            executorService.submit(new SubWorker(randomSleepSec));
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            logger.error("awaitTime error", e);
        }

    }

    class SubWorker implements Runnable {

        private int sleepSec;

        public SubWorker(int sleepSec) {
            this.sleepSec = sleepSec;
        }

        public void run() {
            scheduledComponent.sleep(this.sleepSec);
        }

    }

}
