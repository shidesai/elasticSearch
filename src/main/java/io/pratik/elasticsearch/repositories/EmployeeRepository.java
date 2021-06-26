package io.pratik.elasticsearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import io.pratik.elasticsearch.models.Employee;


public interface EmployeeRepository extends ElasticsearchRepository<Employee, String> {
	 List<Employee> findByFirstName(String name);
	 List<Employee> findByEmailAddress(String name);
	 List<Employee> findByEmailAddressAndFirstName(String name); 

}
