package com.yzhh.backstage.api.config;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.yzhh.backstage.api.commons.RedisTemplateHelper;
import com.yzhh.backstage.api.util.RandomImageUtil;

@Configuration
public class Config {

	 // 资源DB
    @Bean(name = "apiDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.api") // application.properteis中对应属性的前缀
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
	
    @Bean
	public RedisTemplateHelper getRedisTemplateHelper()
	{
		return new RedisTemplateHelper();
	}
    
    @Bean
    public RandomImageUtil getRandomImageUtil() {
    	return new RandomImageUtil();
    }

}
