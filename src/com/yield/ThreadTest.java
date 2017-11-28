package com.yield;

/**
 * 5.线程让步：yield()
 * 
 * 上一篇博文中已经讲到了yield()的基本作用，同时，yield()方法还与线程优先级有关，当某个线程调用yiled()方法从运行状态转换到就绪状态后，CPU从就绪状态线程队列中只会选择与该线程优先级相同或优先级更高的线程去执行。
 *
 */
public class ThreadTest {

	public static void main(String[] args) {
		Thread myThread1 = new MyThread1();
		Thread myThread2 = new MyThread2();
		myThread1.setPriority(Thread.MAX_PRIORITY);
		myThread2.setPriority(Thread.MIN_PRIORITY);
		for (int i = 0; i < 100; i++) {
			System.out.println("main thread i = " + i);
			if (i == 20) {
				myThread1.start();
				myThread2.start();
				Thread.yield();
			}
		}
	}

}

class MyThread1 extends Thread {

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("myThread 1 --  i = " + i);
		}
	}
}

class MyThread2 extends Thread {

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("myThread 2 --  i = " + i);
		}
	}
}
