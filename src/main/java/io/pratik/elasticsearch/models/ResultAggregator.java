package io.pratik.elasticsearch.models;

import org.springframework.data.annotation.Id;

public class ResultAggregator {	
	
	@Id
    private String id;
	
	private String sku_id;
	
	private String first_name;	
	
	private String last_name;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOffice() {
		return office;
	}

	public void setOffice(String office) {
		this.office = office;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip_code() {
		return zip_code;
	}

	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}

	public String getState_province() {
		return state_province;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public String getTelephone_number() {
		return telephone_number;
	}

	public void setTelephone_number(String telephone_number) {
		this.telephone_number = telephone_number;
	}

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
	
	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getEmail_address() {
		return email_address;
	}

	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}

	private String email_address;
	
 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private String name;
    private String sku_description;
    

    ResultAggregator(String sku_id, String name, String sku_description,String first_name,String email_address,String last_name,String full_name,String title, String department,String manager,String description,String office,String city,String zip_code,String state_province,String telephone_number) {
        this.setSku_id(sku_id);
        this.name = name;
        this.setSku_description(sku_description);
        this.setEmail_address(email_address);
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setFull_name(full_name);
        this.setTitle(title);
        this.setDepartment(department);
        this.setManager(manager);
        this.setDescription(description);
        this.setOffice(office);
        this.setCity(city);
        this.setZip_code(zip_code);
        this.setState_province(state_province);
        this.setTelephone_number(telephone_number);
    }

	public String getSku_description() {
		return sku_description;
	}

	public void setSku_description(String sku_description) {
		this.sku_description = sku_description;
	}

	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}

}
