package com.ingesup.truck.service;

/**
 * Created by lopes_f on 3/25/2015.
 * <florian.lopes@outlook.com>
 */
public interface ActivitiService {

	String startProcess(final String processDefinitionKey);

	void claimTask(final String taskId, String username);

	void completeTask(final String processId, final String taskId);

	void getTasksForUser(final String username);
}
