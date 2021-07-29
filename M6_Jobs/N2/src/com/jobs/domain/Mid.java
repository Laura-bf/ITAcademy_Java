package com.jobs.domain;

public class Mid extends Employee{
	public Mid(String name, String address, String phone, double salaryPerMonth, IPaymentRate paymentRate)
			throws Exception {
		super(name, address, phone, salaryPerMonth);
		this.paymentRate = paymentRate;
		
		pay();
		if(super.totalPaid < 1800 || totalPaid > 2500)
			throw new Exception("Mid salary must be between 1800€ and 2500€, both included");
	}
	
	@Override
	public void pay(){
		super.pay();
	}	

	@Override
	public String toString() {
		return "Mid: " + super.toString();
	}
}
