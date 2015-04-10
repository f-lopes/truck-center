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

import java.util.List;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
@Service
public class AlertServiceImpl extends BaseServiceImpl<Alert, String> implements AlertService {

	private final AlertRepository alertRepository;
	private final DriverRepository driverRepository;

	@Autowired
	public AlertServiceImpl(AlertRepository alertRepository, DriverRepository driverRepository) {
		this.alertRepository = alertRepository;
		this.driverRepository = driverRepository;
	}

	@Override
	public BaseRepository<Alert, String> getRepository() {
		return this.alertRepository;
	}

	@Override
	public Alert getFirstByDriverId(String driverId) throws DriverNotFoundException {
		final Driver driver = driverRepository.findOne(driverId);
		if (driver == null) {
			throw new DriverNotFoundException(driverId);
		}

		return this.alertRepository.findFirstByDriver(driver);
	}

	@Override
	public List<Alert> getByDriverId(String driverId) throws DriverNotFoundException {
		final Driver driver = driverRepository.findOne(driverId);
		if (driver == null) {
			throw new DriverNotFoundException(driverId);
		}

		return this.alertRepository.findByDriver(driver);
	}

	@Override
	public List<Alert> getNonResolvedAlerts() {
		return this.alertRepository.findByResolved(false);
	}

	@Override
	public List<Alert> getResolvedAlerts() {
		return this.alertRepository.findByResolved(true);
	}
}
