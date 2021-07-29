package com.jobs.application;

import com.jobs.domain.Boss;
import com.jobs.domain.Junior;
import com.jobs.domain.Manager;
import com.jobs.domain.Senior;
import com.jobs.domain.Mid;
import com.jobs.domain.Volunteer;
import com.jobs.persistence.EmployeeRepository;

public class JobsController {

	private EmployeeRepository repository = new EmployeeRepository();

	public JobsController() {

	}

	public void createBoss(String name, String address, String phone, double salaryPerMonth) throws Exception {
		Boss boss = new Boss(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateBoss());
		repository.addMember(boss);
	}
	
	public void createManager(String name, String address, String phone, double salaryPerMonth)	throws Exception {
		Manager manager = new Manager(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateManager());
		repository.addMember(manager);
	}

	public void createSenior(String name, String address, String phone, double salaryPerMonth) throws Exception {
		Senior senior = new Senior(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateSenior());
		repository.addMember(senior);
	}
	
	public void createMid(String name, String address, String phone, double salaryPerMonth) throws Exception {
		Mid mid = new Mid(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateMid());
		repository.addMember(mid);
	}
	
	public void createJunior(String name, String address, String phone, double salaryPerMonth) throws Exception {
		Junior junior = new Junior(name, address, phone, salaryPerMonth, PaymentFactory.createPaymentRateJunior());
		repository.addMember(junior);
	}
	

	public void payAllEmployeers() throws Exception {
		for (int i = 0; i < repository.getAllMembers().size(); i++) {
			repository.getAllMembers().get(i).pay();
		}

	}

	public String getAllEmployees() {
		String AllEmployees = "";
		for (int i = 0; i < repository.getAllMembers().size(); i++) {
			AllEmployees += repository.getAllMembers().get(i).toString();
		}
		return AllEmployees;

	}

	public void createVolunteer(String name, String address, String phone, String description) throws Exception {
		Volunteer volunteer = new Volunteer(name, address, phone, description);
		repository.addMember(volunteer);

	}

}
