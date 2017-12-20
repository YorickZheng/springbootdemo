package com.example.demo.thread;

import java.util.LinkedList;

/**
 * Created by zhengyilong on 2017/12/20.
 * 多线程学习案例
 * 生产者/消费者模式
 * 仓库类实现缓冲区
 */
public class Storage {
    //仓库最大容量
    private  final int MAX_SIZE = 100;

    //仓库存储的载体
    private LinkedList<Object> list = new LinkedList<Object>();


    //生产num个产品
    public void produce(int num){
        //同步代码段
        synchronized (list){
            //如果仓库剩余充足，则生产条件不满足
            while (list.size() + num > MAX_SIZE ){
                System.out.println("【要生产的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行生产任务!");
                try {
                    //生产条件不满足，产生阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //生产条件满足情况下，生产num个产品
            for (int i = 0;i<num;i++){
                list.add(new Object());
            }

            System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());

            //通知其它线程生产已完成
            list.notifyAll();
        }
    }

    //消费num个产品
    public void consume(int num){
        //同步代码块
        synchronized (list){
            //如果仓库存储量不足,则不能消费
            while (list.size() < num){
                System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:"
                        + list.size() + "\t暂时不能执行消费任务!");
                try {
                    //条件不满足，产生阻塞
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //消费条件满足的情况下，消费num个产品
            for (int i = 0; i < num; i++) {
                list.remove();
            }

            System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());

            //消费完成，通知其他线程
            list.notifyAll();
        }
    }


    // get/set方法
    public LinkedList<Object> getList()
    {
        return list;
    }

    public void setList(LinkedList<Object> list)
    {
        this.list = list;
    }

    public int getMAX_SIZE()
    {
        return MAX_SIZE;
    }
}
