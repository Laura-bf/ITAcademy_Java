package com.jobs.domain;

public class Employee extends AbsStaffMember {

	protected double salaryPerMonth;
	protected IPaymentRate paymentRate;

	public Employee(String name, String address, String phone, double salaryPerMonth)
			throws Exception {
		super(name, address, phone);
		if (salaryPerMonth < 0)
			throw new Exception();
		
		this.salaryPerMonth = salaryPerMonth;
		
	}

	@Override
	public void pay() throws Exception {
		super.totalPaid = paymentRate.pay(salaryPerMonth);
	}

	@Override
	public String toString() {
		return "[id=" + id + ", name=" + name + ", address=" + address
				+ ", phone=" + phone + ", salaryPerMonth=" + salaryPerMonth + ", totalPaid=" + totalPaid + "]\n";
	}

}
