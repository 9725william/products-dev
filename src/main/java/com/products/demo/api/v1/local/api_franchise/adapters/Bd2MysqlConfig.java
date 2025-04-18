package com.products.demo.api.v1.local.api_franchise.adapters;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
	entityManagerFactoryRef = "mysql2EntityManagerFactory", 
	transactionManagerRef = "mysql2TransactionManager"
	, basePackages =  "com.products.demo.api.v1.local.api_franchise"
	, includeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = "com.products.demo.api.v1.local.api_franchise.*.adapters.bd2.*")
	} 
	, excludeFilters = {
		@ComponentScan.Filter(type = FilterType.REGEX, pattern = {"com.products.demo.api.v1.local.api_franchise.*.adapters.bd1.*"})
	}
)
public class Bd2MysqlConfig {

    public String packages_models = "com.products.demo.api.v1.local.api_franchise.*.adapters.bd2";
    
    @Autowired
	public Environment env;
	
	@Bean(name = "mysql2DataSource")
	public DataSource mysql2DataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(env.getProperty("bd2Mysql.datasource.url"));
		dataSource.setUsername(env.getProperty("bd2Mysql.datasource.username"));
		dataSource.setPassword(env.getProperty("bd2Mysql.datasource.password"));
		dataSource.setDriverClassName(env.getProperty("bd2Mysql.datasource.driver-class-name"));
		return dataSource;
	}
        
        
    @Primary
    @Bean(name = "mysql2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(mysql2DataSource());
		em.setPackagesToScan( this.packages_models );
		em.setPersistenceUnitName("Bd2MysqlConfig");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("bd2Mysql.jpa.hibernate.ddl-auto"));
		properties.put("hibernate.show-sql", env.getProperty("bd2Mysql.jpa.show-sql"));
		properties.put("hibernate.dialect", env.getProperty("bd2Mysql.jpa.properties.hibernate.dialect"));
		
		em.setJpaPropertyMap(properties);
		return em;
	}


	@Primary
	@Bean(name = "mysql2TransactionManager")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	public void setEnv(Environment env) {
		this.env = env;
	}
}

