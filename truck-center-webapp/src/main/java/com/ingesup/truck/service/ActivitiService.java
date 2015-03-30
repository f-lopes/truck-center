package com.ingesup.truck.service;

import org.activiti.engine.task.Task;

/**
 * Created by lopes_f on 3/25/2015.
 * <florian.lopes@outlook.com>
 */
public interface ActivitiService {

	Task getCurrentTaskByDriverId(String driverId);

	Task getCurrentTaskByAlertId(String alertId);
}
