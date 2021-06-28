package io.pratik.elasticsearch.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "storeindex")
//REGION,DM,DISTRICT,STORE,STORE_NAME,STREET_ADDRESS,CITY,STATE,ZIP_CODE,PHONE,FAX,HOURS
public class Store {
	@Id
    private String id;
	
	@Field(type = FieldType.Keyword, name = "region")
	private String region;
	@Field(type = FieldType.Text, name = "dm")
	private String dm;	
	@Field(type = FieldType.Keyword, name = "district")
	private String district;
	
	@Field(type = FieldType.Text, name = "store")
	private String store;
	@Field(type = FieldType.Keyword, name = "store_name")
	private String store_name;
	@Field(type = FieldType.Keyword, name = "street_address")
	private String street_address;
	@Field(type = FieldType.Text, name = "city")
	private String city;
	@Field(type = FieldType.Text, name = "state")
	private String state;
	@Field(type = FieldType.Keyword, name = "zip_code")
	private String zip_code;
	@Field(type = FieldType.Keyword, name = "phone")
	private String phone;	
	@Field(type = FieldType.Keyword, name = "hours")
	private String hours;
	@Field(type = FieldType.Keyword, name = "fax")
	private String fax;


}
