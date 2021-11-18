package com.empleados.S402.nivel3.model.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.empleados.S402.nivel3.model.domain.Employee;
import com.empleados.S402.nivel3.model.domain.Job;

@Component
public class EmployeeLoader implements CommandLineRunner{

	@Autowired
	EmployeeRepository employeeRep;
	
	@Override
	public void run(String... args) throws Exception {
		loadEmployees();
	}
	
	private void loadEmployees() {
		if(employeeRep.count()==0) {
			employeeRep.save(new Employee("example1", Job.CLERK));
			employeeRep.save(new Employee("example2", Job.BOSS));
			employeeRep.save(new Employee("example3", Job.MANAGER));
			
		}
	}
}
