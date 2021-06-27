/**
 * 
 */
package io.pratik.elasticsearch.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import io.pratik.elasticsearch.models.Employee;
import io.pratik.elasticsearch.models.Product;
import io.pratik.elasticsearch.models.ResultAggregator;
import io.pratik.elasticsearch.models.Sku;
import io.pratik.elasticsearch.models.Store;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Pratik Das
 */
@Service
@Slf4j
public class ProductSearchService {

	private static final String PRODUCT_INDEX = "productindex";
	private static final String SKU_INDEX = "skuindex";
	private static final String EMP_INDEX = "empindex";
	private static final String STORE_INDEX="storeindex";

	private ElasticsearchOperations elasticsearchOperations;

	@Autowired
	public ProductSearchService(final ElasticsearchOperations elasticsearchOperations) {
		super();
		this.elasticsearchOperations = elasticsearchOperations;
	}

	public List<IndexedObjectInformation> createProductIndexBulk(final List<Product> products) {

		List<IndexQuery> queries = products.stream()
				.map(product -> new IndexQueryBuilder().withId(product.getId().toString()).withObject(product).build())
				.collect(Collectors.toList());
		;

		return elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of(PRODUCT_INDEX));

	}
	
	public String createProductIndex(Product product) {

		IndexQuery indexQuery = new IndexQueryBuilder().withId(product.getId().toString()).withObject(product).build();
		String documentId = elasticsearchOperations.index(indexQuery, IndexCoordinates.of(PRODUCT_INDEX));

		return documentId;
	}

	public void findProductsByBrand(final String brandName) {
		QueryBuilder queryBuilder = QueryBuilders
				.matchQuery("manufacturer", brandName);
		// .fuzziness(0.8)
		// .boost(1.0f)
		// .prefixLength(0)
		// .fuzzyTranspositions(true);

		Query searchQuery = new NativeSearchQueryBuilder()
				.withQuery(queryBuilder)
				.build();

		SearchHits<Product> productHits = 
				elasticsearchOperations
				.search(searchQuery, Product.class,
				  IndexCoordinates.of(PRODUCT_INDEX));

		log.info("productHits {} {}", productHits.getSearchHits().size(), productHits.getSearchHits());

		List<SearchHit<Product>> searchHits = 
				productHits.getSearchHits();
		int i = 0;
		for (SearchHit<Product> searchHit : searchHits) {
			log.info("searchHit {}", searchHit);
		}

	}

	public void findByProductName(final String productName) {
		Query searchQuery = new StringQuery(
				"{\"match\":{\"name\":{\"query\":\""+ productName + "\"}}}\"");

		SearchHits<Product> products = elasticsearchOperations.search(searchQuery, Product.class,
				IndexCoordinates.of(PRODUCT_INDEX));
	}

	public void findByProductPrice(final String productPrice) {
		Criteria criteria = new Criteria("price").greaterThan(10.0).lessThan(100.0);
		Query searchQuery = new CriteriaQuery(criteria);

		SearchHits<Product> products = elasticsearchOperations.search(searchQuery, Product.class,
				IndexCoordinates.of(PRODUCT_INDEX));
	}

	public List<Product> processSearch(final String query) {
		log.info("Search with query {}", query);
		
		// 1. Create query on multiple fields enabling fuzzy search
		QueryBuilder queryBuilder = 
				QueryBuilders
				.multiMatchQuery(query, "name", "description")
				.fuzziness(Fuzziness.AUTO);

		Query searchQuery = new NativeSearchQueryBuilder()
				                .withFilter(queryBuilder)
				                .build();

		// 2. Execute search
		SearchHits<Product> productHits = 
				elasticsearchOperations
				.search(searchQuery, Product.class,
				IndexCoordinates.of(PRODUCT_INDEX));

		// 3. Map searchHits to product list
		List<Product> productMatches = new ArrayList<Product>();
		productHits.forEach(srchHit->{
			productMatches.add(srchHit.getContent());
		});
		return productMatches;
	}
	
	public List<Store> findByStore(final String query){
		log.info("Search with query {}", query);
		
		// 1. Create query on multiple fields enabling fuzzy search
		QueryBuilder queryBuilder = 
				QueryBuilders
				.matchQuery("store", query);
				//.fuzziness(Fuzziness.AUTO);

		Query searchQuery = new NativeSearchQueryBuilder()
				                .withFilter(queryBuilder)
				                .build();
		// CReate a search Source
				
		// 2. Execute search
				SearchHits<Store> productHits = 
						elasticsearchOperations
						.search(searchQuery, Store.class,
						IndexCoordinates.of(STORE_INDEX));

				// 3. Map searchHits to product list
				List<Store> productMatches = new ArrayList<Store>();
				productHits.forEach(srchHit->{
					productMatches.add(srchHit.getContent());					
				});
				return productMatches;
		
	}
	
	public List<ResultAggregator> processSearchNew(final String query) {
		
	//	findByStore(query);
		log.info("Search with query {}", query);
		
		// 1. Create query on multiple fields enabling fuzzy search
		QueryBuilder queryBuilder = 
				QueryBuilders
				.multiMatchQuery(query,"sku_id","sku_description","name","full_name","email_address","store","state","dm","brand","subclass","category","title","zip_code","first_name","last_name","description","city","title","department","telephone_number");
				//.fuzziness(Fuzziness.AUTO);

		Query searchQuery = new NativeSearchQueryBuilder().withFilter(queryBuilder).build();
				

		// 2. Execute search
		SearchHits<ResultAggregator> productHits = 
				elasticsearchOperations
				.search(searchQuery, ResultAggregator.class,
				IndexCoordinates.of(SKU_INDEX,PRODUCT_INDEX,EMP_INDEX,STORE_INDEX));

		// 3. Map searchHits to product list
		List<ResultAggregator> productMatches = new ArrayList<ResultAggregator>();
		productHits.forEach(srchHit->{
			productMatches.add(srchHit.getContent());
		});
		return productMatches;
	}
	

	

	
	public List<String> fetchSuggestions(String query) {
		
		List<String> suggestions = new ArrayList<String>();
		
		QueryBuilder queryBuilder = QueryBuilders
				.wildcardQuery("sku_description", query+"*").caseInsensitive(true);

		Query searchQuery = new NativeSearchQueryBuilder()
				.withFilter(queryBuilder)
				.withPageable(PageRequest.of(0, 5))
				.build();

		SearchHits<Sku> searchSuggestions = 
				elasticsearchOperations.search(searchQuery, 
						Sku.class,
				IndexCoordinates.of(SKU_INDEX));
				
		
		searchSuggestions.getSearchHits().forEach(searchHit->{
			suggestions.add(searchHit.getContent().getDescription());
			suggestions.add(searchHit.getContent().getSkuId());
		});
		//Employee
		QueryBuilder queryBuilder_emp = QueryBuilders
				.wildcardQuery("full_name", query+"*").caseInsensitive(true);

		Query searchQuery_emp = new NativeSearchQueryBuilder()
				.withFilter(queryBuilder_emp)
				.withPageable(PageRequest.of(0, 5))
				.build();

		SearchHits<Employee> searchSuggestions_emp = 
				elasticsearchOperations.search(searchQuery_emp, 
						Employee.class,
				IndexCoordinates.of(EMP_INDEX));
		searchSuggestions_emp.getSearchHits().forEach(searchHit->{
			suggestions.add(searchHit.getContent().getFullName());
			suggestions.add(searchHit.getContent().getEmailAddress());
		});
		
		QueryBuilder queryBuilder_str = QueryBuilders
				.wildcardQuery("state", query+"*").caseInsensitive(true);

		Query searchQuery_str = new NativeSearchQueryBuilder()
				.withFilter(queryBuilder_str)
				.withPageable(PageRequest.of(0, 5))
				.build();

		SearchHits<Store> searchSug_store = elasticsearchOperations.search(searchQuery_str, Store.class,IndexCoordinates.of(STORE_INDEX));
		searchSug_store.getSearchHits().forEach(searchHit->{
			suggestions.add(searchHit.getContent().getStore());
		});
		
		return suggestions;
	}
	
   

}
