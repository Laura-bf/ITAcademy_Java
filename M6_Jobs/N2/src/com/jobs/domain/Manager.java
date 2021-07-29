package com.jobs.domain;

public class Manager extends Employee {
	
	public Manager(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth);
			
		this.paymentRate = paymentRate;
	}

	@Override
	public void pay() throws Exception {
		super.pay();
		if(super.totalPaid < 3000 || totalPaid > 5000)
			throw new Exception("Manager salary must be between 3000€ and 5000€, both included");
	}

	@Override
	public String toString() {
		return "Manager: " + super.toString();
	}
}
