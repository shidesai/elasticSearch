package io.pratik.elasticsearch.productsearchapp;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import io.pratik.elasticsearch.models.Product;
import io.pratik.elasticsearch.models.Sku;
import io.pratik.elasticsearch.repositories.ProductRepository;
import io.pratik.elasticsearch.repositories.SkuRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = "io.pratik.elasticsearch")
public class ProductsearchappApplication {
	
	private static final String COMMA_DELIMITER = ",";

	@Autowired
	private ElasticsearchOperations esOps;

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SkuRepository skuRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProductsearchappApplication.class, args);
	}
	
	@PreDestroy
	public void deleteIndex() {
		esOps.indexOps(Product.class).delete();
		esOps.indexOps(Sku.class).delete();
	}
	
	
	@PostConstruct
	public void buildIndex() {

		esOps.indexOps(Product.class).refresh();
		esOps.indexOps(Sku.class).refresh();
		productRepo.deleteAll();
		productRepo.saveAll(prepareDataset());
		skuRepo.deleteAll();
		skuRepo.saveAll(prepareSkuDataset());
	}
	
	private Collection<Sku> prepareSkuDataset(){

		Resource resource = new ClassPathResource("sku_info.csv");
		List<Sku> skuList = new ArrayList<Sku>();

		try (
			InputStream input = resource.getInputStream();
			Scanner scanner = new Scanner(resource.getInputStream());) {
			int lineNo = 0;
			while (scanner.hasNextLine()) {
				++lineNo;				
				String line = scanner.nextLine();
				if(lineNo == 1) continue;
				Optional<Sku> sku = 
						csvRowToSkuMapper(line);
				if(sku.isPresent())
					skuList.add(sku.get());
			}
		} catch (Exception e) {
			log.error("File read error {}",e);;
		}
		return skuList;
	
		
	}

	private Collection<Product> prepareDataset() {
		Resource resource = new ClassPathResource("fashion-products.csv");
		List<Product> productList = new ArrayList<Product>();

		try (
			InputStream input = resource.getInputStream();
			Scanner scanner = new Scanner(resource.getInputStream());) {
			int lineNo = 0;
			while (scanner.hasNextLine()) {
				++lineNo;				
				String line = scanner.nextLine();
				if(lineNo == 1) continue;
				Optional<Product> product = 
						csvRowToProductMapper(line);
				if(product.isPresent())
				productList.add(product.get());
			}
		} catch (Exception e) {
			log.error("File read error {}",e);;
		}
		return productList;
	}

	private Optional<Product> csvRowToProductMapper(final String line) {
		try (			
			Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				String name = rowScanner.next();
				String description = rowScanner.next();
				String manufacturer = rowScanner.next();
				return Optional.of(
						Product.builder()
						.name(name)
						.description(description)
						.manufacturer(manufacturer)
						.build());

			}
		}
		return Optional.of(null);
	}
	
	private Optional<Sku> csvRowToSkuMapper(final String line) {
		try (			
			Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				String skuId = rowScanner.next();
				String description = rowScanner.next();				
				return Optional.of(
						Sku.builder()						
						.description(description)
						.skuId(skuId)
						.build());

			}
		}
		return Optional.of(null);
	}

}
