package io.pratik.elasticsearch.repositories;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import io.pratik.elasticsearch.models.Sku;

public interface SkuRepository extends ElasticsearchRepository<Sku, String> {
	
	List<Sku> findBySkuIdAndDescription(String skuId,String description);
	List<Sku> findBySkuId(String skuId);
	List<Sku> findByDescription(String skuId);

}
