package com.setPriority;

/**
 * 
 * 4.改变线程的优先级/setPriority()：
 * 
 * 每个线程在执行时都具有一定的优先级，优先级高的线程具有较多的执行机会。每个线程默认的优先级都与创建它的线程的优先级相同。main线程默认具有普通优先级。
 * 
 * 设置线程优先级：setPriority(int
 * priorityLevel)。参数priorityLevel范围在1-10之间，常用的有如下三个静态常量值：
 * 
 * MAX_PRIORITY:10
 * 
 * MIN_PRIORITY:1
 * 
 * NORM_PRIORITY:5
 * 
 * 获取线程优先级：getPriority()。
 * 
 * 注：具有较高线程优先级的线程对象仅表示此线程具有较多的执行机会，而非优先执行。
 *
 */
public class ThreadTest {

	public static void main(String[] args) {
		Thread myThread = new MyThread();
		for (int i = 0; i < 100; i++) {
			System.out.println("main thread i = " + i);
			if (i == 20) {
				myThread.setPriority(Thread.MAX_PRIORITY);
				myThread.start();
			}
		}
	}
}

class MyThread extends Thread {
	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("i = " + i);
		}
	}
}
