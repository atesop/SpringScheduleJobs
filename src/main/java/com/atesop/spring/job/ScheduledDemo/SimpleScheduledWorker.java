package com.atesop.spring.job.ScheduledDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: atesop
 * Date: 16-4-24
 * TIME: 下午4:22
 */
public class SimpleScheduledWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(SimpleScheduledWorker.class);
    private ScheduledComponent scheduledComponent;
    private int subWorkerNum;

    public SimpleScheduledWorker(ScheduledComponent scheduledComponent,int subWorkerNum) {
        this.scheduledComponent = scheduledComponent;
        this.subWorkerNum = subWorkerNum;
    }

    public void run() {
        logger.info("scheduled worker begin");
        scheduledComponent.sleep(subWorkerNum);
        logger.info("scheduled worker end");
    }

}
