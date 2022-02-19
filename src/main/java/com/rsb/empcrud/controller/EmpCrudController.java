package com.rsb.empcrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rsb.empcrud.entity.Employee;
import com.rsb.empcrud.service.EmpCrudService;

@CrossOrigin("*")
@RestController
@RequestMapping("/employee")
public class EmpCrudController {
	
	@Autowired
	public EmpCrudService empService;
	
	@PostMapping("/addData")
	@ResponseBody
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp){
		
		System.out.println("EMPLOYEE ADDED SUCCESSFULLY");
		Employee emp1 = empService.addEmployee(emp);
		
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@GetMapping("/getData/{empId}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("empId") long empId){
		
		Employee emp = empService.getEmployeeById(empId).get();
		if(emp==null) {
			return new ResponseEntity<Employee>(emp,HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		List<Employee> employees = empService.getAllEmployees();
		
		if(employees.isEmpty())
			return new ResponseEntity<List<Employee>>(employees,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
	}
	
	
	@PutMapping("/update/{empId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("empId") long empId ,@RequestBody Employee emp){

		empService.addEmployee(emp);
		
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		
	}
	@DeleteMapping("/delete/{empId}")
	public HttpStatus deleteEmployeeById(@PathVariable("empId") long empId) {
		Employee employee = empService.getEmployeeById(empId).get();
	
		empService.deleteEmployeeById(empId);
		
		return HttpStatus.NO_CONTENT;
	}

}
