package com.atesop.spring.job.ScheduledDemo;

import org.springframework.stereotype.Component;

/**
 * User: atesop
 * Date: 16-4-24
 * TIME: 下午4:22
 */

@Component
public class ScheduledComponent {

    public void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
