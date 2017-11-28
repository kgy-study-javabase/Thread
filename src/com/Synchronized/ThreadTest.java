package com.Synchronized;

public class ThreadTest {

	public static void main(String[] args) {
		Account account = new Account("123456", 1000);
		DrawMoneyRunnable drawMoneyRunnable = new DrawMoneyRunnable(account, 700);
		Thread myThread1 = new Thread(drawMoneyRunnable);
		Thread myThread2 = new Thread(drawMoneyRunnable);
		myThread1.start();
		myThread2.start();
	}

}

class DrawMoneyRunnable implements Runnable {

	private Account account;
	private double drawAmount;

	public DrawMoneyRunnable(Account account, double drawAmount) {
		super();
		this.account = account;
		this.drawAmount = drawAmount;
	}

	public void run() {
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

class Account {

	private String accountNo;
	private double balance;

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

}
