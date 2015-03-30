package com.ingesup.truck.controller;

import com.ingesup.truck.form.AddTruckForm;
import com.ingesup.truck.service.DriverService;
import com.ingesup.truck.service.TruckService;
import com.ingesup.truck.util.MessageUtil;
import com.ingesup.truck_center.model.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
@RequestMapping("/trucks")
public class TruckController {

	private static final String ALL_TRUCKS_VIEW = "truck/allTrucks";
	private static final String ADD_TRUCK_VIEW = "truck/newTruck";
	private static final String ADD_TRUCK_URL = "/trucks/add";
	private static final String ALL_TRUCKS_URL = "/trucks";

	private final TruckService truckService;
	private final DriverService driverService;
	private final MessageSource messageSource;

	@Autowired
	public TruckController(TruckService truckService, DriverService driverService, MessageSource messageSource) {
		this.truckService = truckService;
		this.driverService = driverService;
		this.messageSource = messageSource;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String allTrucks(Model model) {
		model.addAttribute("trucks", truckService.getAll());

		return ALL_TRUCKS_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addTruckView(Model model) {
		model.addAttribute("drivers", driverService.getAll());
		model.addAttribute("addTruckForm", new AddTruckForm());

		return ADD_TRUCK_VIEW;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String handleAddTruck(@Valid AddTruckForm addTruckForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			return "redirect:" + ADD_TRUCK_URL;
		}

		final Truck newTruck = addTruckForm.getTruck();
		newTruck.setDriver(driverService.get(addTruckForm.getDriverId()));

		redirectAttributes.addFlashAttribute("flash", MessageUtil.returnSuccess(
				messageSource.getMessage("truck.add.success", null, LocaleContextHolder.getLocale())));

		return "redirect:" + ALL_TRUCKS_URL;
	}
}
