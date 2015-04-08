package com.ingesup.truckcenter.controller;

import com.ingesup.truckcenter.exception.DriverNotFoundException;
import com.ingesup.truckcenter.model.Alert;
import com.ingesup.truckcenter.security.SecurityUtil;
import com.ingesup.truckcenter.service.ActivitiService;
import com.ingesup.truckcenter.service.AlertService;
import com.ingesup.truckcenter.util.MessageUtil;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {

	private static final String DASHBOARD_VIEW = "dashboard/home";
	private static final String DASHBOARD_URL = "/dashboard";
	private static final String ALERT_VIEW = "alert/view";
	private static final String INCIDENT_BY_DRIVER_VIEW = "dashboard/incident";

	private final AlertService alertService;
	private final MessageSource messageSource;
	private final TaskService taskService;
	private final ActivitiService activitiService;

	@Autowired
	public DashboardController(AlertService alertService, MessageSource messageSource, TaskService taskService,
							   ActivitiService activitiService) {
		this.alertService = alertService;
		this.messageSource = messageSource;
		this.taskService = taskService;
		this.activitiService = activitiService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String alerts(Model model) {
		model.addAttribute("alerts", alertService.getAll());

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

	@RequestMapping(value = "/incident-status-by-driver", method = RequestMethod.GET)
	public String incidentStatusByDriver(Model model, @RequestParam String driverId) {
		final Task activeTask = this.activitiService.getCurrentTaskByBusinessKey(driverId);
		model.addAttribute("activeTask", activeTask);

		return INCIDENT_BY_DRIVER_VIEW;
	}

	@RequestMapping(value = "/complete-task", method = RequestMethod.POST)
	public String completeTask(@RequestParam String taskId, @RequestParam String driverId, RedirectAttributes redirectAttributes) {
		this.activitiService.claimTask(taskId, SecurityUtil.getCurrentLoggedUsername());
		final Task task = this.activitiService.getCurrentTaskByBusinessKey(driverId);

		if (!taskId.equals(task.getId())) {
			redirectAttributes.addFlashAttribute(MessageUtil.returnWarning(
					messageSource.getMessage("complete-task.error", null, LocaleContextHolder.getLocale())));
			return "redirect:" + DASHBOARD_URL;
		}

		taskService.complete(task.getId());

		return "redirect:" + getIncidentsByDriverURL(driverId);
	}

	private String getIncidentsByDriverURL(String driverId) {
		return MvcUriComponentsBuilder
				.fromMethodName(this.getClass(), "incidentsByDriver", null, driverId)
				.toUriString();
	}
}
