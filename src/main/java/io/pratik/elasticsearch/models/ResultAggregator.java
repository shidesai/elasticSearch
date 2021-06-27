package io.pratik.elasticsearch.models;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultAggregator {	
	
	@Id
    private String id;
	
	private String sku_id;
	
	private String first_name;	
	
	private String last_name;
	
	private String brand;
	
	private String  subclass;
	private String category;
	
	private String full_name;
	
	private String title;
	
	private String department;
	
	private String manager;
	
	private String description;
	
	private String office;
	
	private String city;
	
	private String zip_code;
	
	private String state_province;
	
	private String telephone_number;
	
	private String region;
	private String dm;;
	private String district;
	private String store;
	private String store_name;
	private String street_address;
	private String state;
	private String phone;
	private String hours;
	private String fax;
	
	private String name;
    private String sku_description;
    private String email_address;
    private String jda_sku_id;
    private String url;
  //BRAND,SUBCLASS,CATEGORY
  //  REGION,DM,DISTRICT,STORE,STORE_NAME,STREET_ADDRESS,CITY,STATE,ZIP_CODE,PHONE,FAX,HOURS
    

}
