package com.ingesup.truck.service.impl;

import com.ingesup.truck.repository.BaseRepository;
import com.ingesup.truck.repository.DriverRepository;
import com.ingesup.truck.service.DriverService;
import com.ingesup.truck_center.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 3/23/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class DriverServiceImpl extends BaseServiceImpl<Driver, Integer> implements DriverService {

	private final DriverRepository driverRepository;

	@Autowired
	public DriverServiceImpl(DriverRepository driverRepository) {
		this.driverRepository = driverRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return this.driverRepository;
	}
}
