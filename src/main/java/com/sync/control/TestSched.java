package com.sync.control;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestSched {

    @Scheduled(cron="*/5 * * * * ?")
    public void run() {
        System.out.println("sched run");
    }
}
