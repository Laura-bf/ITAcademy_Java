package com.jobs.domain;

public class Manager extends Employee {
	
	public Manager(String name, String address, String phone, double salary, IPaymentRate paymentRate, IRPF irpf)
			throws Exception {
		super(name, address, phone, salary);
			
		this.paymentRate = paymentRate;
		this.irpf = irpf;
		
		pay();
		if(super.salaryPerMonthGross < 3000 || salaryPerMonthGross > 5000)
			throw new Exception("Manager gross salary per month must be between 3000€ and 5000€, both included");
	}


	@Override
	public String toString() {
		return "Manager: " + super.toString();
	}
}
