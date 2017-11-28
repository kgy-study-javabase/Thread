package com.Synchronized;

/**
 * 对共享资源进行访问的方法定义中加上synchronized关键字修饰，使得此方法称为同步方法。可以简单理解成对此方法进行了加锁，其锁对象为当前方法所在的对象自身。多线程环境下，当执行此方法时，首先都要获得此同步锁（且同时最多只有一个线程能够获得），只有当线程执行完此同步方法后，才会释放锁对象，其他的线程才有可能获取此同步锁，以此类推...
 * 
 * 在上例中，共享资源为account对象，当使用同步方法时，可以解决线程安全问题。只需在run()方法前加上synshronized关键字即可。
 *
 */
public class ThreadTest2 {

	public static void main(String[] args) {
		Account2 account = new Account2("123456", 1000);
		DrawMoneyRunnable2 drawMoneyRunnable = new DrawMoneyRunnable2(account, 700);
		Thread myThread1 = new Thread(drawMoneyRunnable);
		Thread myThread2 = new Thread(drawMoneyRunnable);
		myThread1.start();
		myThread2.start();
	}

}

class DrawMoneyRunnable2 implements Runnable {

	private Account2 account;
	private double drawAmount;

	public DrawMoneyRunnable2(Account2 account, double drawAmount) {
		super();
		this.account = account;
		this.drawAmount = drawAmount;
	}

	public synchronized void run() {
		if (account.getBalance() >= drawAmount) { // 1
			System.out.println("取钱成功， 取出钱数为：" + drawAmount);
			double balance = account.getBalance() - drawAmount;
			account.setBalance(balance);
			System.out.println("余额为：" + balance);
		} else {
			System.out.println("余额不足");
		}
	}
}

class Account2 {

	private String accountNo;
	private double balance;

	public Account2() {

	}

	public Account2(String accountNo, double balance) {
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

}
