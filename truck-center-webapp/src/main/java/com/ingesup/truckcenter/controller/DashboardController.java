package com.ingesup.truckcenter.controller;

import com.ingesup.truckcenter.activiti.ActivitiConstants;
import com.ingesup.truckcenter.exception.DriverNotFoundException;
import com.ingesup.truckcenter.model.Alert;
import com.ingesup.truckcenter.security.SecurityUtil;
import com.ingesup.truckcenter.service.ActivitiService;
import com.ingesup.truckcenter.service.AlertService;
import com.ingesup.truckcenter.service.DriverService;
import com.ingesup.truckcenter.util.MessageUtil;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/dashboard")
public class DashboardController {

	private static final String DASHBOARD_VIEW = "dashboard/home";
	private static final String DASHBOARD_URL = "/dashboard";
	private static final String ALERT_VIEW = "alert/view";
	private static final String INCIDENT_BY_DRIVER_VIEW = "dashboard/incident";

	private final AlertService alertService;
	private final DriverService driverService;
	private final MessageSource messageSource;
	private final TaskService taskService;
	private final ActivitiService activitiService;

	@Autowired
	public DashboardController(AlertService alertService, DriverService driverService, MessageSource messageSource, TaskService taskService,
							   ActivitiService activitiService) {
		this.alertService = alertService;
		this.driverService = driverService;
		this.messageSource = messageSource;
		this.taskService = taskService;
		this.activitiService = activitiService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String alerts(Model model, @RequestParam(required = false) Boolean resolved) {
		if (resolved == null) {
			model.addAttribute("alerts", alertService.getNonResolvedAlerts());
		} else {
			if (resolved) {
				model.addAttribute("alerts", alertService.getResolvedAlerts());
			}
		}

		return DASHBOARD_VIEW;
	}

	@RequestMapping(value = "/by-driver", method = RequestMethod.GET)
	public String alertsByDriver(Model model, @RequestParam String driverId, RedirectAttributes redirectAttributes) {
		try {
			model.addAttribute("alerts", alertService.getFirstByDriverId(driverId));
		} catch (DriverNotFoundException e) {
			redirectAttributes.addFlashAttribute(MessageUtil.returnWarning(
					messageSource.getMessage("driver.not-found", null, LocaleContextHolder.getLocale())));

			return "redirect:" + DASHBOARD_URL;
		}

		return DASHBOARD_VIEW;
	}

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public String viewAlert(Model model, @RequestParam String alertId, RedirectAttributes redirectAttributes) {
		// TODO list alerts with associated driver -> link driver click to active state for this driver
		final Alert alert = alertService.get(alertId);
		if (alert == null) {
			redirectAttributes.addFlashAttribute(MessageUtil.returnWarning(
					messageSource.getMessage("alert.not-found", null, LocaleContextHolder.getLocale())));
			return "redirect:" + DASHBOARD_URL;
		}
		final Task activeTask = this.activitiService.getCurrentTaskByAlertId(alertId);

		model.addAttribute("activeTask", activeTask);
		model.addAttribute("alert", alertService.get(alertId));

		return ALERT_VIEW;
	}

	@RequestMapping(value = "/incident-status-by-alert/{alertId}", method = RequestMethod.GET)
	public String incidentStatusByAlert(Model model, @PathVariable String alertId, RedirectAttributes redirectAttributes) {
		final Alert alert = this.alertService.get(alertId);
		if (alert == null) {
			redirectAttributes.addFlashAttribute(MessageUtil.returnWarning(
					messageSource.getMessage("alert.not-found", null, LocaleContextHolder.getLocale())));
			return "redirect:" + DASHBOARD_URL;
		}

		final Task activeTask = this.activitiService.getCurrentTaskByBusinessKey(alertId);
		if (activeTask == null) {
			model.addAttribute("historicProcessInstance", this.activitiService.getEndedProcessByBusinessKey(alertId));
		} else {
			final Map<String, Object> processVariables = activeTask.getProcessVariables();
			if (processVariables != null) {
				model.addAttribute("comment", this.activitiService.getProcessVariables(activeTask.getProcessInstanceId()).get(ActivitiConstants.COMMENT));
			}

			model.addAttribute("activeTask", activeTask);
		}
		model.addAttribute("alert", alert);

		return INCIDENT_BY_DRIVER_VIEW;
	}

	@RequestMapping(value = "/complete-task", method = RequestMethod.POST)
	public String completeTask(@RequestParam String taskId, @RequestParam String alertId, @RequestParam(required = false) String comment,
							   @RequestParam(required = false) Boolean driverResponded, RedirectAttributes redirectAttributes) {
		final Task task = this.activitiService.getCurrentTaskByBusinessKey(alertId);

		if (!taskId.equals(task.getId())) {
			redirectAttributes.addFlashAttribute(MessageUtil.returnWarning(
					messageSource.getMessage("complete-task.error", null, LocaleContextHolder.getLocale())));
			return "redirect:" + DASHBOARD_URL;
		}

		this.activitiService.claimTask(task.getId(), SecurityUtil.getCurrentLoggedUsername());

		if (StringUtils.isNotEmpty(comment)) {
			final Map<String, Object> processVariables = new HashMap<>();
			processVariables.put(ActivitiConstants.COMMENT, comment);

			this.activitiService.addProcessVariables(task.getProcessInstanceId(), processVariables);
		}

		if (driverResponded != null) {
			final Map<String, Object> processVariables = new HashMap<>();
			processVariables.put(ActivitiConstants.DRIVER_RESPONDED, driverResponded);

			this.activitiService.addProcessVariables(task.getProcessInstanceId(), processVariables);
		}

		this.activitiService.completeTask(task.getId());

		// If process has ended, mark alert as resolved
		if (this.activitiService.hasProcessEnded(alertId)) {
			final Alert alert = this.alertService.get(alertId);
			alert.setResolved(true);

			this.alertService.update(alert);
		}

		redirectAttributes.addFlashAttribute(MessageUtil.returnWarning(
				messageSource.getMessage("complete-task.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + getIncidentsByAlertURL(alertId);
	}

	private String getIncidentsByAlertURL(String alertId) {
		return MvcUriComponentsBuilder
				.fromMethodName(this.getClass(), "incidentStatusByAlert", null, alertId, null)
				.toUriString();
	}
}
