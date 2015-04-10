package com.ingesup.truckcenter.config;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * Created by lopes_f on 3/24/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
public class ActivitiConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public ProcessEngineFactoryBean processEngineFactoryBean() {
		ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
		processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration());

		return processEngineFactoryBean;
	}

	public DataSourceTransactionManager dataSourceTransactionManager() {
		return new DataSourceTransactionManager(this.dataSource);
	}

	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration() {
		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
		processEngineConfiguration.setDataSource(this.dataSource);
		processEngineConfiguration.setDatabaseType("mysql");
		processEngineConfiguration.setDeploymentResources(getDeploymentResources());
		processEngineConfiguration.setDatabaseSchema("truck_center");
		processEngineConfiguration.setJpaEntityManagerFactory(this.entityManagerFactory);
		processEngineConfiguration.setTransactionManager(dataSourceTransactionManager());
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		return processEngineConfiguration;
	}

	@Bean
	@Profile("test")
	public SpringProcessEngineConfiguration processEngineConfigurationTest() {
		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
		processEngineConfiguration.setDeploymentResources(getDeploymentResources());
		processEngineConfiguration.setJpaEntityManagerFactory(this.entityManagerFactory);
		processEngineConfiguration.setTransactionManager(dataSourceTransactionManager());
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

		return processEngineConfiguration;
	}

	@Bean
	public RuntimeService runtimeService(SpringProcessEngineConfiguration processEngineConfiguration) {
		return processEngineConfiguration.getRuntimeService();
	}

	@Bean
	public TaskService taskService(SpringProcessEngineConfiguration processEngineConfiguration) {
		return processEngineConfiguration.getTaskService();
	}

	@Bean
	public HistoryService historyService(SpringProcessEngineConfiguration processEngineConfiguration) {
		return processEngineConfiguration.getHistoryService();
	}

	private Resource[] getDeploymentResources() {
		return new ClassPathResource[] {new ClassPathResource("diagram/incident.bpmn")};
	}
}
