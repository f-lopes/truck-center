package com.ingesup.truck.service.impl;

import com.ingesup.truck.exception.DriverNotFoundException;
import com.ingesup.truck.repository.AlertRepository;
import com.ingesup.truck.repository.BaseRepository;
import com.ingesup.truck.repository.DriverRepository;
import com.ingesup.truck.service.AlertService;
import com.ingesup.truck_center.model.Alert;
import com.ingesup.truck_center.model.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class AlertServiceImpl extends BaseServiceImpl<Alert, Integer> implements AlertService {

	private final AlertRepository alertRepository;
	private final DriverRepository driverRepository;

	@Autowired
	public AlertServiceImpl(AlertRepository alertRepository, DriverRepository driverRepository) {
		this.alertRepository = alertRepository;
		this.driverRepository = driverRepository;
	}

	@Override
	public BaseRepository getRepository() {
		return this.alertRepository;
	}

	@Override
	public Alert getFirstByDriverId(Integer driverId) throws DriverNotFoundException {
		final Driver driver = driverRepository.findOne(driverId);
		if (driver == null) {
			throw new DriverNotFoundException(driverId);
		}

		return this.alertRepository.findFirstByDriver(driver);
	}
}
