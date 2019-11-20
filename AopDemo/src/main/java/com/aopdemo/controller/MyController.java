package com.aopdemo.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class MyController {


    @GetMapping("test0")
    public void test0(){
        System.out.println("test0方法");
    }

    @GetMapping("test1")
    public void test1(){
        int i = 10/0;
        System.out.println("test1方法");
    }

    @GetMapping("test2")
    public String test2(@RequestParam(value = "i",required = false) Integer i, @RequestParam("sss") String sss) throws InterruptedException {
        return i+sss;
    }

}
