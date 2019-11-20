package com.timedtask.timedTask;

import com.timedtask.service.TestSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
public class TestTimedTask {

    @Autowired
    TestSerivce testSerivce;

    @Scheduled(cron = "0 * * ? * *")
    public void test(){
        testSerivce.test();
    }

}
