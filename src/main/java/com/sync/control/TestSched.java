package com.sync.control;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TestSched {


    @Scheduled(cron="*/5 * * * * ?")
    public void run() {
        System.out.println("sched run");
    }
}
