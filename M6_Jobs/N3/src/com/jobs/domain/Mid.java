package com.jobs.domain;

public class Mid extends Employee{
	public Mid(String name, String address, String phone, double salary, IPaymentRate paymentRate, IRPF irpf)
			throws Exception {
		super(name, address, phone, salary);
		
		this.paymentRate = paymentRate;
		this.irpf = irpf;
		
		pay();
		if(super.salaryPerMonthGross < 1800 || salaryPerMonthGross > 2500)
			throw new Exception("Mid gross salary per month must be between 1800€ and 2500€, both included");
	}
	
	@Override
	public String toString() {
		return "Mid: " + super.toString();
	}
}
