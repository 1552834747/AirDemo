package com.shardingjdbc;

import org.junit.jupiter.api.Test;

public class Test1 {

    @Test
    public void test0(){
        String a = "card2.0/1194537380569231361.jpg";
        String b = "https://wx.qlogo.cn/mmopen/vi_32/qQ8A3KQ1BZAZSmWnznVjApica5uPYhAAM1XQNRFLZsSdgTiazU1FjuHrvekk8cbC7HJq9ICfED6ga41VibOK30ZvA/132";
        System.out.println(a.startsWith("http"));
        System.out.println(b.startsWith("http"));
    }
}
