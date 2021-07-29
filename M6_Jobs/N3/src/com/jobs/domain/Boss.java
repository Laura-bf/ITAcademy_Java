package com.jobs.domain;

public class Boss extends Employee {

	public Boss(String name, String address, String phone, double salary, IPaymentRate paymentRate, IRPF irpf)
			throws Exception {
		super(name, address, phone, salary);
		
		this.paymentRate = paymentRate;
		this.irpf = irpf;
		
		pay();
		if (super.salaryPerMonthGross < 8000)
			throw new Exception("Boss gross salary per month must be at least 8000€");
	}
	

	@Override
	public String toString() {
		return "Boss: " + super.toString();
	}

}
