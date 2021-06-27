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
@Document(indexName = "empindex")
public class Employee {

	@Id
    private String id;
	
	@Field(type = FieldType.Keyword, name = "serial_no")
	private String serialNo;
	
	@Field(type = FieldType.Text, name = "first_name")
	private String firstName;
	
	@Field(type = FieldType.Text, name = "last_name")
	private String lastName;
	
	@Field(type = FieldType.Text, name = "full_name")
	private String fullName;
	
	@Field(type = FieldType.Keyword, name = "title")
	private String title;
	
	@Field(type = FieldType.Keyword, name = "department")
	private String department;
	
	@Field(type = FieldType.Text, name = "description")
	private String description;
	
	@Field(type = FieldType.Keyword, name = "manager")
	private String manager;
	
	@Field(type = FieldType.Keyword, name = "role")
	private String role;
	
	@Field(type = FieldType.Text, name = "email_address")
	private String emailAddress;
	
	@Field(type = FieldType.Keyword, name = "office")
	private String office;
	
	@Field(type = FieldType.Text, name = "city")
	private String city;
	
	@Field(type = FieldType.Keyword, name = "zip_code")
	private String zipCode;
	
	@Field(type = FieldType.Text, name = "state_province")
	private String stateProvince;
	
	@Field(type = FieldType.Keyword, name = "telephone_number")
	private String telephoneNumber;
	
}
