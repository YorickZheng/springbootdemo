package com.example.demo.thread;

/**
 * Created by zhengyilong on 2017/12/20.
 * 多线程学习案例
 * 生产者/消费者模式
 * 消费者类
 */
public class Consumer extends Thread {
    //每次消费的数量
    private int num;

    //产品存放的仓库
    private Storage storage;

    //构造函数，设置仓库
    public Consumer(Storage storage){
        this.storage = storage;
    }


    //run函数
    @Override
    public void run() {
        consume(this.num);
    }

    //调用仓库Storage的消费函数
    public void consume(int num){
        storage.consume(num);
    }

    // get/set方法
    public int getNum()
    {
        return num;
    }

    public void setNum(int num)
    {
        this.num = num;
    }

    public Storage getStorage()
    {
        return storage;
    }

    public void setStorage(Storage storage)
    {
        this.storage = storage;
    }
}
