package com.threadRunnable.test;

public class ThreadTest {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
			if (i == 30) {
				Runnable myRunnable = new MyRunnable();
				Thread thread = new MyThread(myRunnable);
				thread.start();
			}
		}
	}

}

class MyRunnable implements Runnable {
	private int i = 0;

	@Override
	public void run() {
		System.out.println("in MyRunnable run");
		for (i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}
}

class MyThread extends Thread {

	private int i = 0;

	public MyThread(Runnable runnable) {
		super(runnable);
	}

	@Override
	public void run() {
		System.out.println("in MyThread run");
		for (i = 0; i < 100; i++) {
			System.out.println(Thread.currentThread().getName() + " " + i);
		}
	}
}
