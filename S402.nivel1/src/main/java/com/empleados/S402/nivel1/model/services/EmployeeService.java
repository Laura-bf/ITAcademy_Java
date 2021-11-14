package com.empleados.S402.nivel1.model.services;

import java.util.List;

import com.empleados.S402.nivel1.model.DTO.EmployeeDTO;

public interface EmployeeService {
	
	Integer saveEmployee(EmployeeDTO employeeDTO);
	
	List<EmployeeDTO> getEmployees();
	
	EmployeeDTO getEmployeeById(Integer id);
	
	void deleteById(Integer id);
	
	List<EmployeeDTO> getEmployeesByFilter(String job);

}

