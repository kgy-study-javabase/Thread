package com.join;

/**
 * join —— 让一个线程等待另一个线程完成才继续执行。 如A线程线程执行体中调用B线程的join()方法，则A线程被阻塞，
 * 知道B线程执行完为止，A才能得以继续执行。
 * 
 * @author Admin
 *
 */
public class ThreadTest {

	public static void main(String[] args) {
		MyRunnable myRunnable = new MyRunnable();
		Thread thread = new Thread(myRunnable);

		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 30) {
				thread.start();
				try {
					thread.join(); // main线程需要等待thread线程执行完后才能继续执行
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class MyRunnable implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}

}
