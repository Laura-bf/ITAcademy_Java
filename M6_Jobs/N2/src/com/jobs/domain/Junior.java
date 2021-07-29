package com.jobs.domain;

public class Junior extends Employee {
	public Junior(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth);

		this.paymentRate = paymentRate;
	}
	@Override
	public void pay() throws Exception{
		super.pay();
		if(super.totalPaid < 900 || totalPaid > 1600)
			throw new Exception("Junior salary must be between 900€ and 1600€, both included");
	}
	
	@Override
	public String toString() {
		return "Junior: " + super.toString();
	}
}
