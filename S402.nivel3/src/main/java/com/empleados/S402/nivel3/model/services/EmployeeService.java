package com.empleados.S402.nivel3.model.services;

import java.util.List;

import com.empleados.S402.nivel3.model.DTO.EmployeeDTO;

public interface EmployeeService {
	
	Integer saveEmployee(EmployeeDTO employeeDTO);
	
	List<EmployeeDTO> getEmployees();
	
	EmployeeDTO getEmployeeById(Integer id);
	
	void deleteById(Integer id);
	
	List<EmployeeDTO> getEmployeesByFilter(String job);

}

