package com.ui.automation.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class JsonHelper {

	
private final ObjectMapper objectMapper;
	
	public JsonHelper() {
		// TODO Auto-generated constructor stub
		this.objectMapper = new ObjectMapper();
	}	


	public  <T> T readJsonToPojo(Class<T> clazz, String filePath) throws IOException {
		return objectMapper.readValue(new FileInputStream(filePath), clazz);
	}

	
	public <T> List<T> readJsonToPojoList(Class<T> clazz, String filePath) throws IOException {
		CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		return objectMapper.readValue(new FileInputStream(filePath), listType);
	}

	public Map<Object, Object> readJsonToMap(String filePath) throws IOException {
		return (Map<Object, Object>) objectMapper.readValue(new FileInputStream(filePath),
				new TypeReference<Map<Object, Object>>() {
				});
	}
	
	public <T> T convertMapToGenericPojo(Map<Object, Object> map, Class<T> clazz) {		
		return objectMapper.convertValue(map, clazz);
	}
	
}
