package com.yzhh.backstage.api.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	 // 资源DB
    @Bean(name = "staffDevelopmentDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.staffdevelopment") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
	
  

}
