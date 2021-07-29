package com.jobs.domain;

public class Boss extends Employee {

	public Boss(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth);
		this.paymentRate = paymentRate;
		
		pay();
		if (super.totalPaid< 8000)
			throw new Exception("Boss salary must be at least 8000€");
	}
	
	@Override
	public void pay() {
		super.pay();
	}

	@Override
	public String toString() {
		return "Boss: " + super.toString();
	}

}
