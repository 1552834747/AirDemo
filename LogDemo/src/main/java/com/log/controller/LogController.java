package com.log.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogController {

    private static final Logger logger = LoggerFactory.getLogger(LogController.class);


    @RequestMapping("log1")
    public void log1(){
        logger.info("info=======================");
        logger.error("error=====================");
        logger.debug("debug=====================");
        logger.warn("warn=======================");
        logger.trace("trace=====================");
    }


}
