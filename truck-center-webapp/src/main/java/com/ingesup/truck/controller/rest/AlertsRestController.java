package com.ingesup.truck.controller.rest;

import com.ingesup.truck.activiti.ActivitiConstants;
import com.ingesup.truck.dto.AlertDTO;
import com.ingesup.truck.service.AlertService;
import com.ingesup.truck.service.DriverService;
import com.ingesup.truck_center.model.Alert;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
@RestController
@RequestMapping(value = "/rest")
public class AlertsRestController {

	private static final String DRIVER_STOPPED_FLOW = "";

	private final RuntimeService runtimeService;
	private final AlertService alertService;
	private final DriverService driverService;

	@Autowired
	public AlertsRestController(RuntimeService runtimeService, AlertService alertService, DriverService driverService) {
		this.runtimeService = runtimeService;
		this.alertService = alertService;
		this.driverService = driverService;
	}

	@RequestMapping(value = "/alerts", method = RequestMethod.POST)
	public ResponseEntity<String> createAlert(@RequestBody AlertDTO alertDTO) {
		final Alert alert = new Alert(alertDTO.getDate(), driverService.get(alertDTO.getDriverId()));
		Alert createdAlert = alertService.add(alert);

		Map<String, Object> processVariables = new HashMap<>();
		processVariables.put(ActivitiConstants.ALERT_ID, createdAlert.getId());
		processVariables.put(ActivitiConstants.DRIVER_ID, createdAlert.getDriver().getId());

		ProcessInstance processInstance =
				this.runtimeService.startProcessInstanceByKey(DRIVER_STOPPED_FLOW, processVariables);

		return new ResponseEntity<>(processInstance.getId(), HttpStatus.CREATED);
	}
}
