/**
 * 
 */
package io.pratik.elasticsearch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.pratik.elasticsearch.models.Employee;
import io.pratik.elasticsearch.models.Product;
import io.pratik.elasticsearch.models.ResultAggregator;
import io.pratik.elasticsearch.models.Sku;
import io.pratik.elasticsearch.models.Store;
import io.pratik.elasticsearch.services.ProductSearchService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pratik Das
 *
 */
@RestController
@RequestMapping("/")
@Slf4j
public class SearchController {
	
	private  ProductSearchService searchService;

	@Autowired
	public SearchController(ProductSearchService searchService) { 
	    this.searchService = searchService;
	}
	
	@GetMapping("/products")
	@ResponseBody
	public List<ResultAggregator> fetchByNameOrDesc(@RequestParam(value = "q", required = false) String query) {                         
        log.info("searching by name {}",query);
		List<ResultAggregator> products = searchService.processSearchNew(query) ;
	    log.info("products {}",products);
		return products;
	  }
	
	@GetMapping("/suggestions")
	@ResponseBody
	public List<String> fetchSuggestions(@RequestParam(value = "q", required = false) String query) {                         
        log.info("fetch suggests {}",query);
        List<String> suggests = searchService.fetchSuggestions(query);
        log.info("suggests {}",suggests);
        return suggests;
	  }
	
	@GetMapping("/store")
    @ResponseBody
    public List<Store> fetchStores(@RequestParam(value = "q", required = false) String query) {                        
        log.info("fetch suggests {}",query);
        List<Store> suggests = searchService.findByStore(query);
        log.info("suggests {}",suggests);
        return suggests;
      }
	
	@GetMapping("/employees")
    @ResponseBody
    public List<Employee> fetchEmployee(@RequestParam(value = "q", required = false) String query) {                        
        log.info("fetch suggests {}",query);
        List<Employee> suggests = searchService.findByEmployee(query);
        log.info("suggests {}",suggests);
        return suggests;
      }


}
