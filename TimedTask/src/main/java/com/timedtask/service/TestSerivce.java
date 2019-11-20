package com.timedtask.service;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class TestSerivce {


    @PostConstruct
    public void test(){
        System.out.println(new Date()+"  测试===========================");
    }

}
