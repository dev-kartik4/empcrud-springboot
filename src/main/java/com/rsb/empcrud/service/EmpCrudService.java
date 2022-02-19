package com.rsb.empcrud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.rsb.empcrud.entity.Employee;
import com.rsb.empcrud.repo.EmpCrudRepo;

@Service
public class EmpCrudService {

	@Autowired
	public EmpCrudRepo empRepo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	public SequenceGeneratorService sequenceGeneratorService;
	
	
	/* ADDING EMPLOYEES */
	public Employee addEmployee(Employee emp) {
		
		Optional<Employee> emp1 = empRepo.findById(emp.getEmpId());
		
		if(emp1.isPresent()) {
			Employee employee = new Employee();
			employee.setEmpId(emp.getEmpId());
			employee.setFirstName(emp.getFirstName());
			employee.setLastName(emp.getLastName());
			employee.setPassword(emp.getPassword());
			employee.setPhone(emp.getPhone());
			employee.setAge(emp.getAge());
			
			return employee;
		}else {
			emp.setEmpId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
			emp.setPassword(encoder.encode(emp.getPassword()));
			emp = empRepo.save(emp);
		}
		return emp;
	}
	
	/*GETTING EMPLOYEE BY ID */
	public Optional<Employee> getEmployeeById(long empId) {
		
		return empRepo.findById(empId);
	}
	
	/*GETTING LIST OF ALL EMPLOYEES*/
	public List<Employee> getAllEmployees(){
		
		List<Employee> employees = new ArrayList();
		empRepo.findAll().forEach(employees::add);
		
		return employees;
	}
	
	
	/*DELETING EMPLOYEE BY ID*/
	public void deleteEmployeeById(long empId) {
		
		empRepo.deleteById(empId);
	}
}
