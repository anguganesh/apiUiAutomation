package com.ui.api.automation.config.datapath;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.ui.api.automation.configuration.YamlSourceFactory;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;




@Component
//@PropertySource(value= {"classpath:yamlPathLocation.properties"})
@PropertySource(value= {"classpath:yamlPathLocation.yml"}, factory = YamlSourceFactory.class)
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ui.yaml.filepath")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UiYamlFilePath {
	
	String loginYamlFilePath;	
	String browserYamlFilePath;

}