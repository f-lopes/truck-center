package com.ingesup.truckcenter.service;

import com.ingesup.truckcenter.exception.DriverNotFoundException;
import com.ingesup.truckcenter.model.Alert;

import java.util.List;

/**
 * Created by lopes_f on 3/29/2015.
 * <florian.lopes@outlook.com>
 */
public interface AlertService extends BaseService<Alert, String> {

	Alert getFirstByDriverId(final String driverId) throws DriverNotFoundException;

	List<Alert> getByDriverId(final String driverId) throws DriverNotFoundException;

	List<Alert> getNonResolvedAlerts();

	List<Alert> getResolvedAlerts();
}
