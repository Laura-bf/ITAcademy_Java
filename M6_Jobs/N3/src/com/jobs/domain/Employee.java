package com.jobs.domain;

public class Employee extends AbsStaffMember {
	
	protected static boolean bonus;
	protected double salary;
	protected double salaryPerMonthGross;
	protected double salaryPerMonthNet;
	protected double salaryPerYearGross;
	protected double salaryPerYearNet;
	
	protected IPaymentRate paymentRate;
	protected IRPF irpf;
	

	public Employee(String name, String address, String phone, double salary)
			throws Exception {
		super(name, address, phone);
		if (salary < 0)
			throw new Exception();
		
		this.salary = salary;
		
	}
	
	

	public boolean isBonus() {
		return bonus;
	}

	public static void setBonus(boolean bonus) {
		Employee.bonus = bonus;
	}



	@Override
	public void pay() {
		salaryPerMonthGross = paymentRate.pay(salary);
		salaryPerMonthNet = irpf.pay(salaryPerMonthGross);
		salaryPerYearGross = salaryPerMonthGross*12;
		salaryPerYearNet = salaryPerMonthNet*12;
		
	}
	
	public void calculateBonus() {
		if(bonus) {
			super.extraPay = "Bonus extra of " + (this.salaryPerYearGross*0.1) +"€";
		} else {
			super.extraPay = "No bonus";
		}
	}

	@Override
	public String toString() {
		return "id=" + id + ", Name: " + name + ", Address: " + address
				+ ", Phone:" + phone + "\nMonthGrossSalary=" + salaryPerMonthGross + " /  MonthNetSalary=" + salaryPerMonthNet + " / YearGrossSalary=" 
				+ salaryPerYearGross + " / YearNetSalary=" + salaryPerYearNet + ". / " + super.extraPay + "\n\n";
	}

}
