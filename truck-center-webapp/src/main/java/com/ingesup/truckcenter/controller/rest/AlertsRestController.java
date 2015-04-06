package com.ingesup.truckcenter.controller.rest;

import com.ingesup.truckcenter.activiti.ActivitiConstants;
import com.ingesup.truckcenter.dto.AlertDTO;
import com.ingesup.truckcenter.model.Alert;
import com.ingesup.truckcenter.model.Driver;
import com.ingesup.truckcenter.service.AlertService;
import com.ingesup.truckcenter.service.DriverService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
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
		final Driver driver = driverService.get(alertDTO.getDriverId());

		if (driver != null) {
			final Alert alert = new Alert(alertDTO.getDate(), driverService.get(alertDTO.getDriverId()));
			Alert createdAlert = alertService.add(alert);

			Map<String, Object> processVariables = new HashMap<>();
			processVariables.put(ActivitiConstants.ALERT_ID, createdAlert.getId());
			processVariables.put(ActivitiConstants.DRIVER_ID, createdAlert.getDriver().getId());

			ProcessInstance processInstance =
					this.runtimeService.startProcessInstanceByKey(DRIVER_STOPPED_FLOW, processVariables);

			return new ResponseEntity<>(processInstance.getId(), HttpStatus.CREATED);
		}

		return new ResponseEntity<>(StringUtils.EMPTY, HttpStatus.NOT_FOUND);
	}
}
