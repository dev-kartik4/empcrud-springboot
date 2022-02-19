package com.rsb.empcrud.repo;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.rsb.empcrud.entity.Employee;

@Repository
public interface EmpCrudRepo extends MongoRepository<Employee, Long>{

	Optional<Employee> findById(long empId);

	Employee findByEmail(String email);

}
