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

import io.pratik.elasticsearch.models.Employee;
import io.pratik.elasticsearch.models.Product;
import io.pratik.elasticsearch.models.Sku;
import io.pratik.elasticsearch.models.Store;
import io.pratik.elasticsearch.repositories.EmployeeRepository;
import io.pratik.elasticsearch.repositories.ProductRepository;
import io.pratik.elasticsearch.repositories.SkuRepository;
import io.pratik.elasticsearch.repositories.StoreInfoRepository;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = "io.pratik.elasticsearch")
public class ProductsearchappApplication {
	
	private static final String COMMA_DELIMITER = ",";
	private static final String TAB_DELIMITER = "	";

	@Autowired
	private ElasticsearchOperations esOps;

	@Autowired
	private ProductRepository productRepo;
	
	@Autowired
	private SkuRepository skuRepo;
	
	@Autowired
	private EmployeeRepository empRepo;
	
	@Autowired StoreInfoRepository storeRepo;

	public static void main(String[] args) {
		SpringApplication.run(ProductsearchappApplication.class, args);
	}
	
	@PreDestroy
	public void deleteIndex() {
		esOps.indexOps(Product.class).delete();
		esOps.indexOps(Sku.class).delete();
		esOps.indexOps(Employee.class).delete();
		esOps.indexOps(Store.class).delete();
	}
	
	
	@PostConstruct
	public void buildIndex() {

		esOps.indexOps(Product.class).refresh();
		esOps.indexOps(Sku.class).refresh();
		esOps.indexOps(Employee.class).refresh();
		esOps.indexOps(Store.class).refresh();
		productRepo.deleteAll();
		productRepo.saveAll(prepareDataset());
		skuRepo.deleteAll();
		skuRepo.saveAll(prepareSkuDataset());
		empRepo.deleteAll();
		empRepo.saveAll(prepareEmpDataset());
		storeRepo.deleteAll();
		storeRepo.saveAll(prepareStoreDataset());
	}
	
	private Collection<Sku> prepareSkuDataset(){

		Resource resource = new ClassPathResource("sku_info_new.csv");
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
	
	
	private Collection<Store> prepareStoreDataset(){

		Resource resource = new ClassPathResource("store_info_new.csv");
		List<Store> skuList = new ArrayList<Store>();

		try (
			InputStream input = resource.getInputStream();
			Scanner scanner = new Scanner(resource.getInputStream());) {
			int lineNo = 0;
			while (scanner.hasNextLine()) {
				++lineNo;				
				String line = scanner.nextLine();
				if(lineNo == 1) continue;
				Optional<Store> store = 
						csvRowToStoreMapper(line);
				if(store.isPresent())
					skuList.add(store.get());
			}
		} catch (Exception e) {
			log.error("File read error {}",e);;
		}
		return skuList;
	
		
	}

	private Collection<Product> prepareDataset() {
		Resource resource = new ClassPathResource("fashion-products_new.csv");
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
	
	private Collection<Employee> prepareEmpDataset() {
		Resource resource = new ClassPathResource("active_directory_first1.tsv");
		List<Employee> productList = new ArrayList<Employee>();

		try (
			InputStream input = resource.getInputStream();
			Scanner scanner = new Scanner(resource.getInputStream());) {
			int lineNo = 0;
			while (scanner.hasNextLine()) {
				++lineNo;				
				String line = scanner.nextLine();
				if(lineNo == 1) continue;
				Optional<Employee> employee = 
						csvRowToEmpMapper(line);
				if(employee.isPresent())
				productList.add(employee.get());
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
	//REGION,DM,DISTRICT,STORE,STORE_NAME,STREET_ADDRESS,CITY,STATE,ZIP_CODE,PHONE,FAX,HOURS
	private Optional<Store> csvRowToStoreMapper(final String line) {
		try (			
			Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				String region = rowScanner.next();
				String dm = rowScanner.next();
				String district = rowScanner.next();
				String store = rowScanner.next();
				String store_name = rowScanner.next();
				String street_address = rowScanner.next();
				String city = rowScanner.next();
				String state = rowScanner.next();
				String zip_code = rowScanner.next();
				String phone = rowScanner.next();
				String fax = rowScanner.next();
				String hours = rowScanner.next();
				
				return Optional.of(
						Store.builder()
						.region(region).dm(dm).district(district).store(store).store_name(store_name).street_address(street_address).city(city).state(state).zip_code(zip_code).phone(phone).fax(fax).hours(hours)
						.build());

			}
		}
		return Optional.of(null);
	}
	//BRAND,SUBCLASS,CATEGORY
	private Optional<Sku> csvRowToSkuMapper(final String line) {
		try (			
			Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(COMMA_DELIMITER);
			while (rowScanner.hasNext()) {
				String skuId = rowScanner.next();
				String description = rowScanner.next();	
				String brand = rowScanner.next();
				String subclass = rowScanner.next();	
				String category = rowScanner.next();	
				return Optional.of(
						Sku.builder()						
						.description(description)
						.skuId(skuId)
						.brand(brand)
						.subclass(subclass).category(category)
						.build());

			}
		}
		return Optional.of(null);
	}
	//SERIAL_NO	FIRST_NAME	LAST_NAME	FULL_NAME	TITLE	DEPARTMENT	MANAGER	DESCRIPTION	EMAIL_ADDRESS	OFFICE	CITY	ZIP_CODE	STATE_PROVINCE	TELEPHONE_NUMBER
	private Optional<Employee> csvRowToEmpMapper(final String line) {
		try (			
			Scanner rowScanner = new Scanner(line)) {
			rowScanner.useDelimiter(TAB_DELIMITER);
			while (rowScanner.hasNext()) {
				String serialNo = rowScanner.next();				
				String lastName = rowScanner.next();				
				String fullName = rowScanner.next();
				String title = rowScanner.next();
				String department = rowScanner.next();
				String manager = rowScanner.next();
				String description = rowScanner.next();
				String emailAddress = rowScanner.next();
				String office = rowScanner.next();
				String city = rowScanner.next();
				String zipCode = rowScanner.next();
				String stateProvince = rowScanner.next();
				String telephoneNumber = rowScanner.next();
				String firstName = rowScanner.next();
				
				return Optional.of(
						Employee.builder()
						.serialNo(serialNo)
						.firstName(firstName)
						.lastName(lastName)
						.fullName(fullName).title(title).department(department).manager(manager).description(description).emailAddress(emailAddress).office(office).city(city).zipCode(zipCode).stateProvince(stateProvince).telephoneNumber(telephoneNumber)
						.build());

			}
		}
		return Optional.of(null);
	}

}
