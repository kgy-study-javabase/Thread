package com.Synchronized;

/**
 * 
 * 正如上面所分析的那样，解决线程安全问题其实只需限制对共享资源访问的不确定性即可。使用同步方法时，使得整个方法体都成为了同步执行状态，会使得可能出现同步范围过大的情况，于是，针对需要同步的代码可以直接另一种同步方式——同步代码块来解决。
 * 
 * 同步代码块的格式为：
 * 
 * 1 synchronized (obj) { 2 3 //... 4 5 }
 * 其中，obj为锁对象，因此，选择哪一个对象作为锁是至关重要的。一般情况下，都是选择此共享资源对象作为锁对象。
 * 
 * 如上例中，最好选用account对象作为锁对象。（当然，选用this也是可以的，那是因为创建线程使用了runnable方式，如果是直接继承Thread方式创建的线程，使用this对象作为同步锁会其实没有起到任何作用，因为是不同的对象了。因此，选择同步锁时需要格外小心...）
 */
public class ThreadTest21 {

	public static void main(String[] args) {
		Account21 account = new Account21("123456", 1000);

		DrawMoneyThread21 myThread1 = new DrawMoneyThread21(account, 700);
		DrawMoneyThread21 myThread2 = new DrawMoneyThread21(account, 700);
		myThread1.start();
		myThread2.start();
	}

}

/*
 * class DrawMoneyRunnable21 implements Runnable {
 * 
 * private Account21 account; private double drawAmount;
 * 
 * public DrawMoneyRunnable21(Account21 account, double drawAmount) { super();
 * this.account = account; this.drawAmount = drawAmount; }
 * 
 * public synchronized void run() { if (account.getBalance() >= drawAmount) { //
 * 1 System.out.println("取钱成功， 取出钱数为：" + drawAmount); double balance =
 * account.getBalance() - drawAmount; account.setBalance(balance);
 * System.out.println("余额为：" + balance); } else { System.out.println("余额不足"); }
 * } }
 */

class DrawMoneyThread21 extends Thread {
	private Account21 account;
	private double drawAmount;

	public DrawMoneyThread21(Account21 account, double drawAmount) {
		super();
		this.account = account;
		this.drawAmount = drawAmount;
	}

	@Override
	public void run() {
		synchronized (account) {
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
}

class Account21 {

	private String accountNo;
	private double balance;

	public Account21() {

	}

	public Account21(String accountNo, double balance) {
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
