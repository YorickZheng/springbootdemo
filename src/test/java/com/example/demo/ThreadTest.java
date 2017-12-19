package com.example.demo;

import com.example.demo.thread.ThreadUtil;
import org.junit.Test;

/**
 * Created by zhengyilong on 2017/12/19.
 */
public class ThreadTest {

    @Test
    public void hello(){
        ThreadUtil threadUtil = new ThreadUtil();
        threadUtil.run();
    }


    @Test
    public void produce(){
        ThreadUtil pro = new ThreadUtil();//生产者
        for (int i=0;i<11;i++){
            pro.produce();
        }
        System.out.print("生产者产品数量"+pro.product+"\n");
        ThreadUtil con = new ThreadUtil();//消费者
        con.product = pro.product;//生产者给予消费者产品
        for (int i=0;i<11;i++){
            con.consume();
        }
        System.out.print("消费者产品数量"+con.product+"\n");
    }

}
