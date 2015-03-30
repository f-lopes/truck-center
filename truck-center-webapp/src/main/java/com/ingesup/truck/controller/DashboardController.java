package com.ingesup.truck.controller;

import com.ingesup.truck.activiti.ActivitiConstants;
import com.ingesup.truck.exception.DriverNotFoundException;
import com.ingesup.truck.service.AlertService;
import com.ingesup.truck.util.MessageUtil;
import com.ingesup.truck_center.model.Alert;
import org.activiti.engine.RuntimeService;
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
	private final RuntimeService runtimeService;
	private final TaskService taskService;

	@Autowired
	public DashboardController(AlertService alertService, MessageSource messageSource,
							   RuntimeService runtimeService, TaskService taskService) {
		this.alertService = alertService;
		this.messageSource = messageSource;
		this.runtimeService = runtimeService;
		this.taskService = taskService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String alerts(Model model) {
		model.addAttribute("alerts", alertService.getAll());

		return DASHBOARD_VIEW;
	}

	@RequestMapping(value = "/by-driver", method = RequestMethod.GET)
	public String alertsByDriver(Model model, @RequestParam Integer driverId, RedirectAttributes redirectAttributes) {
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
	public String viewAlert(Model model, @RequestParam Integer alertId, RedirectAttributes redirectAttributes) {
		final Alert alert = alertService.get(alertId);
		if (alert == null) {
			redirectAttributes.addFlashAttribute(MessageUtil.returnWarning(
					messageSource.getMessage("alert.not-found", null, LocaleContextHolder.getLocale())));
			return "redirect:" + DASHBOARD_URL;
		}
		final Task activeTask = taskService.createTaskQuery().active().processVariableValueEquals(ActivitiConstants.ALERT_ID, alertId).singleResult();

		model.addAttribute("activeTask", activeTask);
		model.addAttribute("alert", alertService.get(alertId));

		return ALERT_VIEW;
	}

	@RequestMapping(value = "/incidents-by-driver", method = RequestMethod.GET)
	public String incidentsByDriver(Model model, @RequestParam Integer driverId) {
		final Task activeTask = taskService.createTaskQuery().active().processVariableValueEquals(ActivitiConstants.DRIVER_ID, driverId).singleResult();
		model.addAttribute("activeTask", activeTask);

		return INCIDENT_BY_DRIVER_VIEW;
	}
}
