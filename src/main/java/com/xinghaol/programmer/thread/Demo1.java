package com.xinghaol.programmer.thread;

/**
 * @author: lixinghao
 * @date: 2020/5/28 6:35 下午
 * @Description:
 */
public class Demo1 {
    private boolean flag = false;
    Object object = new Object();

    public void load() {
        int i = 0;
        Thread thread = Thread.currentThread();
        //程序空跑优先级非常高 不会发生上下文切换
        while (!flag) {
            //可能产生竞争和阻塞 阻塞将导致上下文切换  会将工作内存中信息回写到任务状态段
            //切换回来时 从状态段中读取工作内存信息  有可能从主存中重新load内存
//            synchronized (object){
//                i++;
//            }
//            System.out.println(i++);
        }
        System.out.println("当前线程: " + thread.getName() + " 感知到了flag的变化");
    }

    public void refresh() {
        this.flag = true;
        Thread thread = Thread.currentThread();
        System.out.println("当前线程 : " + thread.getName() + " 修改了共享变量flag");
        System.out.println(flag);
    }

    public static void main(String[] args) throws InterruptedException {
        Demo1 demo1 = new Demo1();
        Thread threadA = new Thread(() -> demo1.load(), "线程A");

        Thread threadB = new Thread(() -> demo1.refresh(), "线程B");
        threadA.start();
        Thread.sleep(1000);
        threadB.start();
    }
}
