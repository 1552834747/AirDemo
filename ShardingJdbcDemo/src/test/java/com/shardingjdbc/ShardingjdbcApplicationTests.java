package com.shardingjdbc;

import com.shardingjdbc.dao.TestDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
class ShardingjdbcApplicationTests {

    @Autowired
    private TestDao testDao;

    @Test
    void contextLoads() {
        List<Map<String, Object>> list = testDao.list();
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
    }

    @Test
    public void test1(){
        System.out.println(999999999999999L);
    }

}
