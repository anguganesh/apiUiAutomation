package com.ui.automation.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;



public class YamlHelper {

	private final ObjectMapper yamlMapper;
	
	public YamlHelper() {
		// TODO Auto-generated constructor stub
		this.yamlMapper = new ObjectMapper(new YAMLFactory());
	}
	
	public <T> T readYamlToPojo(Class<T> clazz, String filePath) throws IOException {
		return yamlMapper.readValue(new FileInputStream(filePath), clazz);
	}

	public <T> List<T> readYamlToPojoList(Class<T> clazz, String filePath) throws IOException {
		CollectionType listType = yamlMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		return yamlMapper.readValue(new FileInputStream(filePath), listType);
	}

	@SuppressWarnings("unchecked")
	public <T> Map<String, T> readYamlToMap(Class<T> clazz, String filePath) throws IOException {
		return (Map<String, T>) yamlMapper.readValue(new FileInputStream(filePath), clazz);
	}

	public <T> List<T> readMapToPojo(Class<T> clazz, Map<String, T> map) {
		CollectionType listType = yamlMapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		return yamlMapper.convertValue(map, listType);
	}

	public Map readPojoObjectToMap(Object classObj) {
		return yamlMapper.convertValue(classObj, Map.class);
	}


	public Object readMapToGenericPOJO(Map<String, String> map, Class clazz) {
		final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		final Object pojo = mapper.convertValue(map, clazz);
		return pojo;
	}
	
	// the following method should parse basic, non-nested yaml into Map<K,V>
	public Map<String, Object> parseFlatYamlToMap(String yamlFilePath) {
	    Map<String, Object> yamlAsMap = new HashMap<>();
	    Yaml yaml = new Yaml();
	    try {
	      yamlAsMap = yaml.load(new FileInputStream(yamlFilePath));
	    } catch (Exception e) {
	      System.out.println("Error in the file " + yamlFilePath);
	      System.out.println(Arrays.toString(e.getStackTrace()));
	    }
	    return yamlAsMap;
	  }
}
