package io.pratik.elasticsearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import io.pratik.elasticsearch.models.Employee;
import io.pratik.elasticsearch.models.Store;

public interface StoreInfoRepository extends ElasticsearchRepository<Store, String> {
	
	List<Store> findByCity(String city);
	List<Store> findByState(String state);
	List<Store> findByStore(String store);
	List<Employee> findByDm(String name); 
	List<Employee> findByStoreAndDm(String name);

}
