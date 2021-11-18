package com.empleados.S402.nivel3.model.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empleados.S402.nivel3.model.DTO.EmployeeDTO;
import com.empleados.S402.nivel3.model.domain.Employee;
import com.empleados.S402.nivel3.model.domain.Job;
import com.empleados.S402.nivel3.model.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRep;
	

	@Override
	public Integer saveEmployee(EmployeeDTO employeeDTO) {
		Employee employee = this.mapDtoToEntity(employeeDTO);
		employee = employeeRep.save(employee);
		return employee.getPk_ID();
	}

	@Override
	public List<EmployeeDTO> getEmployees() {
		List<EmployeeDTO> dtoList = null;
		List<Employee> employees = employeeRep.findAll();
		if(employees != null && employees.size()>0) 
			dtoList = employees.stream().map(e -> this.mapEntityToDto(e)).collect(Collectors.toList());
		
		return dtoList;
	}

	@Override
	public EmployeeDTO getEmployeeById(Integer id) {
		EmployeeDTO dto = null;
		Optional<Employee> employee = employeeRep.findById(id);
		if (employee.isPresent())
			dto = this.mapEntityToDto(employeeRep.getById(id));
		else
			dto = new EmployeeDTO();
		return dto;
	}

	@Override
	public void deleteById(Integer id) {
		employeeRep.deleteById(id);
		
	}

	@Override
	public List<EmployeeDTO> getEmployeesByFilter(String job) {
		List<EmployeeDTO> dtoList = null;
		
		List<Employee> employees = employeeRep.findByJob(Job.valueOf(job));
		if(employees!=null && employees.size()>0) 
			dtoList = employees.stream().map(e -> this.mapEntityToDto(e)).collect(Collectors.toList());
		
		return dtoList;
	}
	
	//para convertir un dto en un objeto persistente
	private Employee mapDtoToEntity(EmployeeDTO employeeDto) {
		Employee employee = new Employee();
		if(employeeDto.getPk_ID() != null)
			employee.setPk_ID(employeeDto.getPk_ID());
		employee.setName(employeeDto.getName());
		employee.setJob(employeeDto.getJob());

		return employee;

	}
	//para convertir un objeto persistente en un dto
	private EmployeeDTO mapEntityToDto(Employee employee) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setPk_ID(employee.getPk_ID());
		dto.setName(employee.getName());
		dto.setJob(employee.getJob());
		dto.setPhoto();
		if(employee.getJob()==Job.CLERK)
			dto.setSalary(1000d);
		else if(employee.getJob()==Job.BOSS)
			dto.setSalary(3000d);
		else
			dto.setSalary(6000d);
	
		return dto;
	}

}
