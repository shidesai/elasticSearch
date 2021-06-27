package io.pratik.elasticsearch.models;

import org.springframework.data.annotation.Id;

public class ResultAggregator {	
	
	@Id
    private String id;
	
	private String sku_id;
	
	private String first_name;	
	
	private String last_name;
	
	
	
	private String brand;
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	private String  subclass;
	private String category;
	
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
	
	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDm() {
		return dm;
	}

	public void setDm(String dm) {
		this.dm = dm;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public String getStore_name() {
		return store_name;
	}

	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}

	public String getStreet_address() {
		return street_address;
	}

	public void setStreet_address(String street_address) {
		this.street_address = street_address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

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
  //BRAND,SUBCLASS,CATEGORY
  //  REGION,DM,DISTRICT,STORE,STORE_NAME,STREET_ADDRESS,CITY,STATE,ZIP_CODE,PHONE,FAX,HOURS
    ResultAggregator(String sku_id, String name, String sku_description,String first_name,String email_address,String last_name,String full_name,String title, String department,String manager,String description,String office,String city,String zip_code,String state_province,String telephone_number,
    	String region,String dm,String district,String store,String store_name,String street_address,String state, String phone, String hours, String fax, String brand,String subclass,String category ) {
        this.setSku_id(sku_id);
        this.name = name;
        this.setBrand(brand);
        this.setSubclass(subclass);
        this.setCategory(category);
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
        this.setRegion(region);
        this.setDm(dm);
        this.setDistrict(district);
        this.setStore(store_name);
        this.setStore_name(store_name);
        this.setStreet_address(street_address);
        this.setState(state);
        this.setPhone(phone);
        this.setFax(fax);
        this.setHours(hours);
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
