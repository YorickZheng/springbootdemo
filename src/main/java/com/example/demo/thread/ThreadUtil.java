package com.example.demo.thread;

/**
 * Created by zhengyilong on 2017/12/19.
 */
public class ThreadUtil implements Runnable {

    public  static  final  int MAX_ = 10;

    public  static  final  int MIN_ = 1;

    public  int product;


    @Override
    public void run() {
        System.out.print("hello thread\n");
    }

    /**
     * 生产产品
     */
    public synchronized  void produce(){
        if(this.product >= MAX_){
            try {
                wait();
                System.out.print("产品已满\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        this.product++;
        System.out.print("生产1个产品\n");
        notifyAll();
    }

    /**
     * 消费产品
     */
    public synchronized void  consume(){
        if(this.product < MIN_){
            try {
                wait();
                System.out.print("不能消费了\n");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return;
        }
        this.product--;
        System.out.print("消费1个产品\n");
        notifyAll();
    }

}
