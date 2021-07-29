package com.jobs.domain;

public class Junior extends Employee {
	public Junior(String name, String address, String phone, double salary, IPaymentRate paymentRate, IRPF irpf)
			throws Exception {
		super(name, address, phone, salary);

		this.paymentRate = paymentRate;
		this.irpf = irpf;
		
		pay();
		if(super.salaryPerMonthGross < 900 || salaryPerMonthGross > 1600)
			throw new Exception("Junior gross salary per month must be between 900€ and 1600€, both included");
	}
	
	@Override
	public String toString() {
		return "Junior: " + super.toString();
	}
}
