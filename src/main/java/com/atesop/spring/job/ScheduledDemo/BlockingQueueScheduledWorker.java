package com.atesop.spring.job.ScheduledDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;

/**
 * User: atesop
 * Date: 16-4-24
 * TIME: 下午4:22
 */
public class BlockingQueueScheduledWorker implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(BlockingQueueScheduledWorker.class);
    private ScheduledComponent scheduledComponent;
    private BlockingQueue<Integer> integerBlockingQueue;

    public BlockingQueueScheduledWorker(ScheduledComponent scheduledComponent, BlockingQueue<Integer> integerBlockingQueue) {
        this.scheduledComponent = scheduledComponent;
        this.integerBlockingQueue = integerBlockingQueue;
    }

    public void run() {
        logger.info("scheduled worker begin");
        Integer sleepSec = null;
        try {
            while ((sleepSec = integerBlockingQueue.take()) != null) {
                scheduledComponent.sleep(sleepSec);
            }
        } catch (InterruptedException e) {
            logger.error(e.toString());
        }
        logger.info("scheduled worker end");
    }

}
