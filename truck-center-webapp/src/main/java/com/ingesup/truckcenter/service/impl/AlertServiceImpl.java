package com.ingesup.truckcenter.service.impl;

import com.ingesup.truckcenter.exception.DriverNotFoundException;
import com.ingesup.truckcenter.model.Alert;
import com.ingesup.truckcenter.model.Driver;
import com.ingesup.truckcenter.repository.AlertRepository;
import com.ingesup.truckcenter.repository.BaseRepository;
import com.ingesup.truckcenter.repository.DriverRepository;
import com.ingesup.truckcenter.service.AlertService;
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
	public BaseRepository<Alert, Integer> getRepository() {
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