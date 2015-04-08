package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.activiti.ActivitiConstants;
import com.ingesup.truckcenter.service.ActivitiService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

	private final RuntimeService runtimeService;
	private final TaskService taskService;

	@Autowired
	public ActivitiServiceImpl(RuntimeService runtimeService, TaskService taskService) {
		this.runtimeService = runtimeService;
		this.taskService = taskService;
	}

	@Override
	public Task getCurrentTaskByBusinessKey(String driverId) {
		return this.taskService
				.createTaskQuery()
				.processInstanceBusinessKey(driverId)
				.active()
				.singleResult();
	}

	@Override
	public Task getCurrentTaskByDriverId(String driverId) {
		return this.taskService
				.createTaskQuery()
				.active()
				.processVariableValueEquals(ActivitiConstants.DRIVER_ID, driverId)
				.singleResult();
	}

	@Override
	public Task getCurrentTaskByAlertId(String alertId) {
		return this.taskService
				.createTaskQuery()
				.active()
				.processVariableValueEquals(ActivitiConstants.ALERT_ID, alertId)
				.singleResult();
	}

	@Override
	public void claimTask(String taskId, String userId) {
		this.taskService.claim(taskId, userId);
	}
}
