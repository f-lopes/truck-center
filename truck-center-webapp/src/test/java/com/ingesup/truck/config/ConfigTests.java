package com.ingesup.truck.config;

import com.ingesup.truck.TruckCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TruckCenterApplication.class)
@ActiveProfiles("test")
public class ConfigTests {

	@Test
	public void configurationLoads() {

	}
}
