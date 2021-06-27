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
@Document(indexName = "skuindex")
public class Sku {
	//SKU_ID,SKU_DESCRIPTION,BRAND,SUBCLASS,CATEGORY
	@Id
    private String id;
	
	@Field(type = FieldType.Keyword, name = "sku_id")
	private String skuId;
	
	@Field(type = FieldType.Text, name = "sku_description")
	private String description;
	
	@Field(type = FieldType.Text, name = "brand")
	private String brand;

	@Field(type = FieldType.Keyword, name = "subclass")
	private String subclass;

	@Field(type = FieldType.Text, name = "category")
	private String category;
	
	@Field(type = FieldType.Text, name = "jda_sku_id")
	private String atgSkuId;


	

}
