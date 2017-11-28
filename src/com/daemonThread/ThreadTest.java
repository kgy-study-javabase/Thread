package com.daemonThread;

/**
 * 3.后台线程（Daemon Thread）
 * 
 * 概念/目的：后台线程主要是为其他线程（相对可以称之为前台线程）提供服务，或“守护线程”。如JVM中的垃圾回收线程。
 * 
 * 生命周期：后台线程的生命周期与前台线程生命周期有一定关联。主要体现在：当所有的前台线程都进入死亡状态时，后台线程会自动死亡(其实这个也很好理解，因为后台线程存在的目的在于为前台线程服务的，既然所有的前台线程都死亡了，那它自己还留着有什么用...伟大啊
 * ! !)。
 * 
 * 设置后台线程：调用Thread对象的setDaemon(true)方法可以将指定的线程设置为后台线程。
 * 
 * @author Admin
 *
 */
public class ThreadTest {
	public static void main(String[] args) {
		Thread myThread = new MyThread();
		for (int i = 0; i < 100; i++) {
			System.out.println("main thread i = " + i);
			if (i == 20) {
				myThread.setDaemon(true);
				myThread.start();
			}
		}
	}

}

class MyThread extends Thread {

	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("i = " + i);
			System.out.println("isDaemon:"+ Thread.currentThread().isDaemon());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}