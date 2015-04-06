package com.ingesup.truckcenter.controller;

import com.ingesup.truckcenter.model.Truck;
import com.ingesup.truckcenter.service.TruckService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@RunWith(MockitoJUnitRunner.class)
public class TruckControllerTests {

	private static final String ALL_TRUCKS_URL = "/trucks";
	private static final String TRUCKS_VIEW_NAME = "truck/list";

	private final Truck[] TRUCKS = {new Truck("1"), new Truck("2")};

	private MockMvc mockMvc;

	@Mock
	private TruckService truckService;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders
				.standaloneSetup(new TruckController(this.truckService, null, null))
				.alwaysDo(print())
				.build();

		when(truckService.getAll())
				.thenReturn(Arrays.asList(TRUCKS));
	}

	@Test
	public void testAllTrucks() throws Exception {
		this.mockMvc.perform(get(ALL_TRUCKS_URL))
				.andExpect(status().is2xxSuccessful())
				.andExpect(view().name(TRUCKS_VIEW_NAME))
				.andExpect(model().attribute("trucks", Arrays.asList(TRUCKS)));
	}
}
