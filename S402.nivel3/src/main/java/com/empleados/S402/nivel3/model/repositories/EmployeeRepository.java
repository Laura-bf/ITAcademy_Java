package com.empleados.S402.nivel3.model.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empleados.S402.nivel3.model.domain.Employee;
import com.empleados.S402.nivel3.model.domain.Job;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	public List<Employee> findByJob(Job job);

}
