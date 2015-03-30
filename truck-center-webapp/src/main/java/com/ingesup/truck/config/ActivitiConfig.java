package com.ingesup.truck.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * Created by lopes_f on 3/24/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
public class ActivitiConfig {

	@Autowired
	private DataSource dataSource;

	@Bean
	public ProcessEngineFactoryBean processEngineFactoryBean() {
		ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
		processEngineFactoryBean.setProcessEngineConfiguration(processEngineConfiguration());

		return processEngineFactoryBean;
	}

	@Bean
	public SpringProcessEngineConfiguration processEngineConfiguration() {
		SpringProcessEngineConfiguration processEngineConfiguration = new SpringProcessEngineConfiguration();
		processEngineConfiguration.setDataSource(this.dataSource);
		processEngineConfiguration.setDeploymentResources(getDeploymentResources());
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

	private Resource[] getDeploymentResources() {
		return new ClassPathResource[] {new ClassPathResource("truck-center-driver-stuck-flow.bpmn20.xml")};
	}
}
