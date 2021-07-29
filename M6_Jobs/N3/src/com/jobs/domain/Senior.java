package com.jobs.domain;

public class Senior extends Employee {
	
	public Senior(String name, String address, String phone, double salary, IPaymentRate paymentRate, IRPF irpf)
			throws Exception {
		super(name, address, phone, salary);
		
		this.paymentRate = paymentRate;
		this.irpf = irpf;
		
		pay();
		if(super.salaryPerMonthGross < 2700 || salaryPerMonthGross > 4000)
			throw new Exception("Senior gross salary per month must be between 2700€ and 4000€, both included");
	}

	@Override
	public String toString() {
		return "Senior: " + super.toString();
	}

}
