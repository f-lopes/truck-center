package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.activiti.ActivitiConstants;
import com.ingesup.truckcenter.service.ActivitiService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by lopes_f on 3/30/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class ActivitiServiceImpl implements ActivitiService {

	private final RuntimeService runtimeService;
	private final TaskService taskService;
	private final HistoryService historyService;

	@Autowired
	public ActivitiServiceImpl(RuntimeService runtimeService, TaskService taskService, HistoryService historyService) {
		this.runtimeService = runtimeService;
		this.taskService = taskService;
		this.historyService = historyService;
	}

	@Override
	public Task getCurrentTaskByBusinessKey(String alertId) {
		return this.taskService
				.createTaskQuery()
				.processInstanceBusinessKey(alertId)
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

	@Override
	public void completeTask(String taskId) {
		this.taskService.complete(taskId);
	}

	@Override
	public void addProcessVariables(String processInstanceId, Map<String, Object> variables) {
		this.runtimeService.setVariables(processInstanceId, variables);
	}

	@Override
	public HistoricProcessInstance getEndedProcessByBusinessKey(String alertId) {
		return this.historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(alertId).finished().singleResult();
	}

	@Override
	public Map<String, Object> getProcessVariables(String processInstanceId) {
		return this.runtimeService.getVariables(processInstanceId);
	}

	@Override
	public boolean hasProcessEnded(String alertId) {
		return this.getEndedProcessByBusinessKey(alertId) != null;
	}
}
