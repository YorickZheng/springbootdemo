package com.example.demo.thread;

/**
 * Created by zhengyilong on 2017/12/20.
 * 多线程学习实例
 * 生产者/消费者模式
 * 生产者类继承线程类Thread
 */
public class Producer extends Thread {

    //每次生产的产品数量
    private int num;

    //产品存放的仓库
    private Storage storage;

    //构造函数，设置仓库
    public Producer(Storage storage) {
        this.storage = storage;
    }

    //run函数
    @Override
    public void run() {
        produce(this.num);
    }

    //调用仓库Storage的生产函数
    public void produce(int num) {
        storage.produce(num);
    }

    // get/set方法
    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Storage getStorage() {
        return storage;
    }

    public void setStorage(Storage storage) {
        this.storage = storage;
    }
}

