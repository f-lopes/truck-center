package com.ingesup.truckcenter.controller;

import com.ingesup.truckcenter.form.AddDriverForm;
import com.ingesup.truckcenter.model.Driver;
import com.ingesup.truckcenter.model.RoleEnum;
import com.ingesup.truckcenter.service.DriverService;
import com.ingesup.truckcenter.service.TruckService;
import com.ingesup.truckcenter.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/drivers")
@Secured("ROLE_ADMIN")
public class DriverController {

	private static final String DRIVERS_VIEW = "driver/list";
	private static final String ADD_DRIVER_VIEW = "driver/add";
	private static final String ADD_DRIVER_URL = "/drivers/add";
	private static final String ALL_DRIVERS_URL = "/drivers";

	private final DriverService driverService;
	private final TruckService truckService;
	private final MessageSource messageSource;

	@Autowired
	public DriverController(DriverService driverService, TruckService truckService, MessageSource messageSource) {
		this.driverService = driverService;
		this.truckService = truckService;
		this.messageSource = messageSource;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String allDrivers(Model model) {
		model.addAttribute("drivers", driverService.getAll());

		return DRIVERS_VIEW;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addDriverForm(Model model) {
		model.addAttribute("addDriverForm", new AddDriverForm());
		model.addAttribute("trucks", this.truckService.getAll());

		return ADD_DRIVER_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String handleAddDriver(@Valid AddDriverForm addDriverForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "redirect:" + ADD_DRIVER_URL;
		}

		final Driver newDriver = addDriverForm.getDriverFromForm();
		newDriver.addRole(RoleEnum.ROLE_DRIVER);
		this.driverService.add(newDriver);

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("driver.add.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + ALL_DRIVERS_URL;
	}
}
