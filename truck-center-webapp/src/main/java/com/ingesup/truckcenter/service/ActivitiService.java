package com.ingesup.truckcenter.service;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.task.Task;

import java.util.Map;

/**
 * Created by lopes_f on 3/25/2015.
 * <florian.lopes@outlook.com>
 */
public interface ActivitiService {

	Task getCurrentTaskByBusinessKey(String alertId);

	Task getCurrentTaskByDriverId(String driverId);

	Task getCurrentTaskByAlertId(String alertId);

	void claimTask(String taskId, String userId);

	void completeTask(String taskId);

	void addProcessVariables(String processInstanceId, Map<String, Object> variables);

	HistoricProcessInstance getEndedProcessByBusinessKey(String alertId);

	Map<String, Object> getProcessVariables(String processInstanceId);

	boolean hasProcessEnded(String alertId);
}
