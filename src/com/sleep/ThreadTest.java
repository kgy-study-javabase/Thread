package com.sleep;

/**
 * sleep ——
 * 让当前的正在执行的线程暂停指定的时间，并进入阻塞状态。在其睡眠的时间段内，该线程由于不是处于就绪状态，因此不会得到执行的机会。即使此时系统中没有任何其他可执行的线程，出于sleep()中的线程也不会执行。因此sleep()方法常用来暂停线程执行。
 * 
 * 前面有讲到，当调用了新建的线程的start()方法后，线程进入到就绪状态，可能会在接下来的某个时间获取CPU时间片得以执行，如果希望这个新线程必然性的立即执行，直接调用原来线程的sleep(1)即可。
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
					Thread.sleep(1);   // 使得thread必然能够马上得以执行
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
