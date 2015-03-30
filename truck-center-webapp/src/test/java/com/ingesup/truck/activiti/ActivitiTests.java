package com.ingesup.truck.activiti;

import com.ingesup.truck.TruckCenterApplication;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TruckCenterApplication.class)
public class ActivitiTests {

	private static final java.lang.String TRUCK_CENTER_PROCESS = "";
	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@Autowired
	@Rule
	public ActivitiRule activitiSpringRule;

	@Test
	@Deployment
	public void simpleProcessTest() {
		runtimeService.startProcessInstanceByKey(TRUCK_CENTER_PROCESS);
		Task task = taskService.createTaskQuery().singleResult();
		assertEquals("task", task.getName());

		taskService.complete(task.getId());
		assertEquals(0, runtimeService.createProcessInstanceQuery().count());
	}

}
