package com.ingesup.truck.controller;

import com.ingesup.truck.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Controller
@RequestMapping("/trucks")
public class TruckController {

	private static final String ALL_TRUCKS_VIEW = "truck/list";

	private final TruckService truckService;

	@Autowired
	public TruckController(TruckService truckService) {
		this.truckService = truckService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String allTrucks(Model model) {
		model.addAttribute("trucks", truckService.getAll());

		return ALL_TRUCKS_VIEW;
	}
}
