package com.jobs.application;

import java.util.List;

import com.jobs.domain.AbsStaffMember;
import com.jobs.domain.Boss;
import com.jobs.domain.Employee;
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
	
	//para poder activar el bono del 10% para los empleados desde el main
	public static void setBonus(String option) throws Exception {
		if(option.equalsIgnoreCase("on")) {
			Employee.setBonus(true);
		}else if(option.equalsIgnoreCase("off")) {
			Employee.setBonus(false);
		}else throw new Exception("Invalid option for bonus");
	}
	
	public void calculateBonus() {
		List<AbsStaffMember> lista = repository.getAllMembers();
		for(AbsStaffMember a : lista) {
			if (a instanceof Employee) {
				Employee e = (Employee) a;
				e.calculateBonus();
			} 
		}
	}
	
	public void createBoss(String name, String address, String phone, double salary) throws Exception {
		Boss boss = new Boss(name, address, phone, salary, PaymentFactory.createPaymentRateBoss(), IRPFFactory.createIRPFBoss());
		repository.addMember(boss);
	}
	
	public void createManager(String name, String address, String phone, double salary)	throws Exception {
		Manager manager = new Manager(name, address, phone, salary, PaymentFactory.createPaymentRateManager(), IRPFFactory.createIRPFManager());
		repository.addMember(manager);
	}

	public void createSenior(String name, String address, String phone, double salary) throws Exception {
		Senior senior = new Senior(name, address, phone, salary, PaymentFactory.createPaymentRateSenior(), IRPFFactory.createIRPFSenior());
		repository.addMember(senior);
	}
	
	public void createMid(String name, String address, String phone, double salary) throws Exception {
		Mid mid = new Mid(name, address, phone, salary, PaymentFactory.createPaymentRateMid(), IRPFFactory.createIRPFMid());
		repository.addMember(mid);
	}
	
	public void createJunior(String name, String address, String phone, double salary) throws Exception {
		Junior junior = new Junior(name, address, phone, salary, PaymentFactory.createPaymentRateJunior(), IRPFFactory.createIRPFJunior());
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

	public void createVolunteer(String name, String address, String phone, boolean aid, double aidAmount) throws Exception {
		Volunteer volunteer = new Volunteer(name, address, phone, aid, aidAmount);
		repository.addMember(volunteer);

	}
	


}
