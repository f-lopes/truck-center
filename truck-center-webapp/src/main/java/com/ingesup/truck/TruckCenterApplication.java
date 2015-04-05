package com.ingesup.truck;

import com.ingesup.truck.config.ActivitiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Configuration
@ComponentScan(basePackages = "com.ingesup.truck")
@EnableAutoConfiguration
@EntityScan("com.ingesup.truck_center.model")
@EnableJpaRepositories(basePackages = "com.ingesup.truck.repository")
@Import(ActivitiConfig.class)
public class TruckCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(TruckCenterApplication.class, args);
	}
}
