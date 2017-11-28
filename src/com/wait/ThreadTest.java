package com.wait;

/**
 * 在博文《Java总结篇系列：java.lang.Object》中有提及到这三个方法，虽然这三个方法主要都是用于多线程中，但实际上都是Object类中的本地方法。因此，理论上，任何Object对象都可以作为这三个方法的主调，在实际的多线程编程中，只有同步锁对象调这三个方法，才能完成对多线程间的线程通信。
 * 
 * wait()：导致当前线程等待并使其进入到等待阻塞状态。直到其他线程调用该同步锁对象的notify()或notifyAll()方法来唤醒此线程。
 * 
 * notify()：唤醒在此同步锁对象上等待的单个线程，如果有多个线程都在此同步锁对象上等待，则会任意选择其中某个线程进行唤醒操作，只有当前线程放弃对同步锁对象的锁定，才可能执行被唤醒的线程。
 * 
 * notifyAll()：唤醒在此同步锁对象上等待的所有线程，只有当前线程放弃对同步锁对象的锁定，才可能执行被唤醒的线程。
 *
 */
public class ThreadTest {

	public static void main(String[] args) {
		Account account = new Account("123456", 0);

		Thread drawMoneyThread = new DrawMoneyThread("取钱线程", account, 700);
		Thread depositeMoneyThread = new DepositeMoneyThread("存钱线程", account, 700);

		drawMoneyThread.start();
		depositeMoneyThread.start();
	}

}

class DrawMoneyThread extends Thread {

	private Account account;
	private double amount;

	public DrawMoneyThread(String threadName, Account account, double amount) {
		super(threadName);
		this.account = account;
		this.amount = amount;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			account.draw(amount, i);
		}
	}
}

class DepositeMoneyThread extends Thread {

	private Account account;
	private double amount;

	public DepositeMoneyThread(String threadName, Account account, double amount) {
		super(threadName);
		this.account = account;
		this.amount = amount;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			account.deposite(amount, i);
		}
	}
}

class Account {

	private String accountNo;
	private double balance;
	// 标识账户中是否已有存款
	private boolean flag = false;

	public Account() {

	}

	public Account(String accountNo, double balance) {
		this.accountNo = accountNo;
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * 存钱
	 * 
	 * @param depositeAmount
	 */
	public synchronized void deposite(double depositeAmount, int i) {

		if (flag) {
			// 账户中已有人存钱进去，此时当前线程需要等待阻塞
			try {
				System.out.println(Thread.currentThread().getName() + " 开始要执行wait操作" + " -- i=" + i);
				wait();
				// 1
				System.out.println(Thread.currentThread().getName() + " 执行了wait操作" + " -- i=" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			// 开始存钱
			System.out.println(Thread.currentThread().getName() + " 存款:" + depositeAmount + " -- i=" + i);
			setBalance(balance + depositeAmount);
			flag = true;

			// 唤醒其他线程
			notifyAll();

			// 2
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "-- 存钱 -- 执行完毕" + " -- i=" + i);
		}
	}

	/**
	 * 取钱
	 * 
	 * @param drawAmount
	 */
	public synchronized void draw(double drawAmount, int i) {
		if (!flag) {
			// 账户中还没人存钱进去，此时当前线程需要等待阻塞
			try {
				System.out.println(Thread.currentThread().getName() + " 开始要执行wait操作" + " 执行了wait操作" + " -- i=" + i);
				wait();
				System.out.println(Thread.currentThread().getName() + " 执行了wait操作" + " 执行了wait操作" + " -- i=" + i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			// 开始取钱
			System.out.println(Thread.currentThread().getName() + " 取钱：" + drawAmount + " -- i=" + i);
			setBalance(getBalance() - drawAmount);

			flag = false;

			// 唤醒其他线程
			notifyAll();

			System.out.println(Thread.currentThread().getName() + "-- 取钱 -- 执行完毕" + " -- i=" + i); // 3
		}
	}

}
